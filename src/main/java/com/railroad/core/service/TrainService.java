package com.railroad.core.service;

import com.railroad.core.mapper.StationMapper;
import com.railroad.core.mapper.TrainMapper;
import com.railroad.core.mq.MessageSender;
import com.railroad.model.dao.ScheduleDao;
import com.railroad.model.dao.TrainDao;
import com.railroad.model.dto.*;
import com.railroad.model.entity.Direction;
import com.railroad.model.entity.Schedule;
import com.railroad.model.entity.Train;
import com.railroad.model.search.Graph;
import com.railroad.model.search.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TrainService {

    private final
    StationService stationService;

    private final
    StationMapper stationMapper;

    private final
    TrainDao trainDao;

    private final
    ScheduleDao scheduleDao;

    private final
    TrainMapper trainMapper;

    private final
    WagonService wagonService;

    private final
    DirectionService directionService;

    private final
    MessageSender messageSender;

    @Autowired
    public TrainService(TrainDao trainDao, TrainMapper trainMapper, WagonService wagonService, DirectionService directionService, ScheduleDao scheduleDao, MessageSender messageSender, StationService stationService, StationMapper stationMapper) {
        this.trainDao = trainDao;
        this.trainMapper = trainMapper;
        this.wagonService = wagonService;
        this.directionService = directionService;
        this.scheduleDao = scheduleDao;
        this.messageSender = messageSender;
        this.stationService = stationService;
        this.stationMapper = stationMapper;
    }

    /**
     * Gets list of all trains.
     *
     * @return list of all trains DTO's.
     */
    public List<TrainDto> getAllTrains() {
        return trainMapper.trainListToTrainDtoList(trainDao.getAllTrains());
    }

    /**
     * Gets train DTO by train id.
     *
     * @param id train id.
     * @return train DTO.
     */
    public TrainDto getTrainById(Long id) {
        //if train not exist, then return empty trainDto
        if (id == null | trainDao.getTrainById(id) == null) {
            return getNewTrainDto();
        }
        TrainDto trainDto = trainMapper.trainToTrainDto(trainDao.getTrainById(id));
        if (trainDto == null) {
            return getNewTrainDto();
        }
        //sort schedules
        if (trainDto.getSchedules() == null || trainDto.getSchedules().isEmpty()) {
            return trainDto;
        }
        Collections.sort(trainDto.getSchedules(),
                (o1, o2) -> o1.getScheduleId().compareTo(o2.getScheduleId()));
        return trainDto;
    }

    /**
     * Gets list of all wagon types.
     *
     * @return list of all wagon type's DTO's.
     */
    public List<WagonTypeDto> getAllWagonTypes() {
        return wagonService.getAllWagonTypes();
    }

    /**
     * Saves or updates train. If train doe's not exist,
     * then save, else update.
     *
     * @param trainDto saved/updated train DTO.
     * @return new or current train id.
     */
    public Long saveOrUpdateTrain(TrainDto trainDto) {
        if (trainDto == null) {
            return null;
        }
        if (trainDto.getTrainId() == null || trainDao.getTrainById(trainDto.getTrainId()) == null) {
            if (trainDto.getTrainNumber() == null || trainDao.getTrainByTrainNumber(trainDto.getTrainNumber()) != null) {
                return null;
            }
            Train train = trainMapper.trainDtoToTrain(trainDto);
            if (train == null) {
                return null;
            }
            //save train and get new id
            return trainDao.saveTrain(train);
        } else {
            Train checkTrain = trainDao.getTrainByTrainNumber(trainDto.getTrainNumber());
            if (checkTrain != null) {
                if (!Objects.equals(checkTrain.getTrainId(), trainDto.getTrainId())) {
                    return null;
                }
            }
            //update train
            Train updateTrain = trainMapper.trainDtoToTrain(trainDto);
            if (updateTrain != null) {
                trainDao.updateTrain(updateTrain);
            }
            trainDao.updateTrain(trainMapper.trainDtoToTrain(trainDto));
            return trainDto.getTrainId();
        }
    }

    /**
     * Removes train by id.
     *
     * @param id train id.
     */
    public void removeTrainById(Long id) {
        if (id == null || trainDao.getTrainById(id) == null) {
            return;
        }
        trainDao.removeTrain(id);
    }

    public SearchResultDto getSearchResult(Long depStationId, Long arrStationId, String fromTime, String toTime) {
        SearchResultDto searchResult = new SearchResultDto();
        searchResult.setDepStationId(depStationId);
        searchResult.setArrStationId(arrStationId);
        searchResult.setFromTime(fromTime);
        searchResult.setToTime(toTime);
        List<StationDto> path = getNodes(depStationId, arrStationId);
        List<StationDto> branch = new ArrayList<>();
        int count = 0;
        while (path.size() != 0) {
            String from = "00:00";
            String to = "23:59";
            if (count == 0) {
                from = fromTime;
                to = toTime;
            }
            List<TrainDto> trains =
                    getTrainsByDepStationIdAndArrStationId(
                            path.get(0).getStationId(),
                            path.get(path.size() - 1).getStationId(),
                            from,
                            to);
            if (trains.size() != 0) {
                PathTrainsDto pathTrains = new PathTrainsDto();
                pathTrains.setTrains(trains);
                for (StationDto s :
                        path) {
                    pathTrains.getStations().add(s);
                }
                searchResult.getPathTrains().add(pathTrains);
                if (branch.size() != 0) {
                    branch.add(path.get(path.size() - 1));
                }
                path.clear();
                path.addAll(branch);
                Collections.reverse(path);
                branch.clear();
                count++;
            } else {
                branch.add(path.get(path.size() - 1));
                path.remove(path.size() - 1);
            }
        }
        return searchResult;

    }

    private List getNodes(Long depStationId, Long arrStationId) {
        ArrayList<Node> nodes = new ArrayList<>();
        List<StationDto> stations = stationService.getAllStations();
        for (StationDto s :
                stations) {
            Node node = new Node();
            node.setStation(s);
            node.setSearched(false);
            List<Direction> directions = directionService.getDirectionListByDepStationId(s.getStationId());
            for (Direction d :
                    directions) {
                node.getEdges().add(stationMapper.stationToStationDto(d.getStationByArrStationId()));
            }
            nodes.add(node);
        }
        Graph graph = new Graph();
        graph.setNodes(nodes);

        Node startNode = graph.getNodes()
                .stream().filter(x -> Objects.equals(x.getStation().getStationId(), depStationId)).findFirst().get();
        Node goalNode = graph.getNodes()
                .stream().filter(x -> Objects.equals(x.getStation().getStationId(), arrStationId)).findFirst().get();

        return search(startNode, goalNode, graph);
    }

    private List search(Node startNode, Node goalNode, Graph graph) {
        // list of visited nodes
        LinkedList closedList = new LinkedList();

        // list of nodes to visit (sorted)
        LinkedList openList = new LinkedList();
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node node = (Node) openList.removeFirst();
            if (node.getStation().getStationId().equals(goalNode.getStation().getStationId())) {
                // path found!
                return constructPath(goalNode);
            } else {
                closedList.add(node);

                // add neighbors to the open list
                Iterator i = node.getEdges().iterator();
                while (i.hasNext()) {
                    StationDto neighborStation = (StationDto) i.next();
                    Node neighborNode = graph.getNodes()
                            .stream().filter(x -> Objects.equals(x.getStation().getStationId(), neighborStation.getStationId())).findFirst().get();
                    ;
                    if (!closedList.contains(neighborNode) &&
                            !openList.contains(neighborNode)) {
                        neighborNode.setParent(node);
                        openList.add(neighborNode);
                    }
                }
            }
        }
        // no path found
        return null;
    }

    private List constructPath(Node node) {
        LinkedList path = new LinkedList();
        path.addFirst(node.getStation());
        do {
            node = node.getParent();
            path.addFirst(node.getStation());
        } while (node.getParent() != null);
        return path;
    }


    public List<TrainDto> getTrainsByDepStationIdAndArrStationId(Long depStationId, Long arrStationId, String fromTime, String toTime) {
        List<TrainDto> trainDtos = new ArrayList<>();
        List<Direction> departureDirection = directionService.getDirectionListByDepStationId(depStationId);
        List<Direction> arriveDirection = directionService.getDirectionListByArrStationId(arrStationId);
        List<Schedule> schedules = new ArrayList<>();
        for (Direction d :
                departureDirection) {
            schedules.addAll(scheduleDao.getScheduleListByDirectionId(d.getDirectionId()));
        }
        List<Long> trainIds = new ArrayList<>();
        for (Schedule s :
                schedules) {
            for (Direction d :
                    arriveDirection) {
                List<Long> ids = scheduleDao.getTrainIdsByTrainIdAndDirectionId(s.getTrainId(), d.getDirectionId());
                trainIds.addAll(ids);
            }
        }
        for (Long l :
                trainIds) {
            trainDtos.add(trainMapper.trainToTrainDto(trainDao.getTrainById(l)));
        }
        List<TrainDto> removeTrainsDtos = new ArrayList<>();
        for (TrainDto t :
                trainDtos) {
            Long depScheduleId = 0L;
            Long arrScheduleId = 0L;
            for (ScheduleDto s :
                    t.getSchedules()) {
                if (Objects.equals(s.getDirectionByDirectionId().getDepStationId(), depStationId)) {
                    depScheduleId = s.getScheduleId();
                    if (!isTimeBetweenTwoTime(fromTime, toTime, s.getDepartureTime())) {
                        removeTrainsDtos.add(t);
                        break;
                    }
                }
                if (Objects.equals(s.getDirectionByDirectionId().getArrStationId(), arrStationId)) {
                    arrScheduleId = s.getScheduleId();
                }
                if (depScheduleId != 0L && arrScheduleId != 0L && depScheduleId > arrScheduleId) {
                    removeTrainsDtos.add(t);
                }
            }
        }
        trainDtos.removeAll(removeTrainsDtos);
        return trainDtos;
    }

    public void enableOrDisableTrainById(Long id) {
        Train train = trainDao.getTrainById(id);
        if (train.getEnabled()) {
            trainDao.enableOrDisableTrainById(id, false);
            TrainJMSDto trainJMSDto = trainMapper.trainToTrainJMSDto(trainDao.getTrainById(id));
            messageSender.sendMessage(trainJMSDto);
        } else {
            trainDao.enableOrDisableTrainById(id, true);
            TrainJMSDto trainJMSDto = trainMapper.trainToTrainJMSDto(trainDao.getTrainById(id));
            messageSender.sendMessage(trainJMSDto);
        }
    }

    public void setShiftByTrainId(Long id, Integer shift) {
        Train train = trainDao.getTrainById(id);
        if (train != null) {
            trainDao.setShiftByTrainId(id, shift);
            TrainJMSDto trainJMSDto = trainMapper.trainToTrainJMSDto(trainDao.getTrainById(id));
            messageSender.sendMessage(trainJMSDto);
        }
    }

    private boolean isTimeBetweenTwoTime(String initialTime, String finalTime, String currentTime) {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9])$";
        if (initialTime.matches(reg) && finalTime.matches(reg) && currentTime.matches(reg)) {
            try {
                //Start Time
                Date inTime = new SimpleDateFormat("HH:mm").parse(initialTime);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(inTime);
                //Current Time
                Date checkTime = new SimpleDateFormat("HH:mm").parse(currentTime);
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(checkTime);
                //End Time
                Date finTime = new SimpleDateFormat("HH:mm").parse(finalTime);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(finTime);
                if (finalTime.compareTo(initialTime) < 0) {
                    calendar2.add(Calendar.DATE, 1);
                    calendar3.add(Calendar.DATE, 1);
                }
                Date actualTime = calendar3.getTime();
                if ((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime()) == 0) && actualTime.before(calendar2.getTime())) {
                    return true;
                }
            } catch (ParseException e) {
                return false;
            }
        }
        return false;
    }

    private TrainDto getNewTrainDto() {
        return new TrainDto(0L, "", true);
    }


}
