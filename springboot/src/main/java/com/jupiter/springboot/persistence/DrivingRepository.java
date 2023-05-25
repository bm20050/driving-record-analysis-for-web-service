package com.jupiter.springboot.persistence;

import com.jupiter.springboot.domain.Driving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrivingRepository extends JpaRepository<Driving, Long> {

    List<Driving> findByPlateAndYearAndMonthAndDay(String plate, String year, String month, String day);

    List<Driving> findByPlateLikeAndYearAndMonthAndDay(String plate, String year, String month, String day);

    List<Driving> findByYearAndMonthAndDay(String year, String month, String day);

    @Query("SELECT d FROM Driving d WHERE (d.year=?1 and d.month=?2 and d.day=?3) and (d.suddenAcc =?4 or d.suddenDeparture=?5 or d.suddenDrop=?6 or d.suddenStop=?7)")
    List<Driving> findTotal(String year, String month, String day, int acc, int dep, int drop, int stop);

    @Query("SELECT d FROM Driving d WHERE (d.plate=?8 and d.year=?1 and d.month=?2 and d.day=?3) and (d.suddenAcc =?4 or d.suddenDeparture=?5 or d.suddenDrop=?6 or d.suddenStop=?7)")
    List<Driving> findTotalPlate(String year, String month, String day, int acc, int dep, int drop, int stop, String plate);

    List<Driving> findByYearAndMonthAndDayAndSuddenAcc(String year, String month, String day, int acc);

    List<Driving> findByYearAndMonthAndDayAndSuddenDeparture(String year, String month, String day, int dep);

    List<Driving> findByYearAndMonthAndDayAndSuddenDrop(String year, String month, String day, int drop);

    List<Driving> findByYearAndMonthAndDayAndSuddenStop(String year, String month, String day, int stop);

    List<Driving> findByPlateLikeAndYearAndMonthAndDayAndSuddenAcc(String plate, String year, String month, String day, int acc);

    List<Driving> findByPlateLikeAndYearAndMonthAndDayAndSuddenDeparture(String plate, String year, String month, String day, int dep);

    List<Driving> findByPlateLikeAndYearAndMonthAndDayAndSuddenDrop(String plate, String year, String month, String day, int drop);

    List<Driving> findByPlateLikeAndYearAndMonthAndDayAndSuddenStop(String plate, String year, String month, String day, int stop);

}
