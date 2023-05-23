package com.jupiter.springboot.persistence;

import com.jupiter.springboot.domain.Driving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrivingRepository extends JpaRepository<Driving, Long> {

    List<Driving> findByPlateAndYearAndMonthAndDay(String plate, String year, String month, String day);

    List<Driving> findByPlateLikeAndYearAndMonthAndDay(String plate, String year, String month, String day);

    List<Driving> findByYearAndMonthAndDay(String year, String month, String day);

    List<Driving> findByYearAndMonthAndDayAndSuddenAccGreaterThanEqual(String year, String month, String day, int acc);

    List<Driving> findByPlateLikeAndYearAndMonthAndDayAndSuddenAccGreaterThanEqual(String plate, String year, String month, String day, int acc);

    List<Driving> findByYearAndMonthAndDayAndSuddenAccGreaterThanEqualOrSuddenDepartureGreaterThanEqual(String year, String month, String day, int acc, int dep);

    @Query("SELECT d FROM Driving d WHERE (d.year=?1 and d.month=?2 and d.day=?3) and (d.suddenAcc =?4 or d.suddenDeparture=?5 or d.suddenDrop=?6 or d.suddenStop=?7)")
    List<Driving> findTotal(String year, String month, String day, int acc, int dep, int drop, int stop);

    @Query("SELECT d FROM Driving d WHERE (d.plate=?8 and d.year=?1 and d.month=?2 and d.day=?3) and (d.suddenAcc =?4 or d.suddenDeparture=?5 or d.suddenDrop=?6 or d.suddenStop=?7)")
    List<Driving> findPlate(String year, String month, String day, int acc, int dep, int drop, int stop, String plate);


}
