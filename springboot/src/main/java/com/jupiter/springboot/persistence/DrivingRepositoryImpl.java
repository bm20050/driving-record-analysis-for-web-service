package com.jupiter.springboot.persistence;

import com.jupiter.springboot.domain.Driving;
import com.jupiter.springboot.dto.ReqParams;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.jupiter.springboot.domain.QDriving.driving;

@RequiredArgsConstructor
public class DrivingRepositoryImpl implements DrivingRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Driving> searchDriving(ReqParams reqParams) {

        return queryFactory.select(driving)
                .from(driving)
                .where(
                        plateEq(reqParams.getBusNumber()),
                        dangerEq(reqParams.getDanger()),
                        driving.year.eq(reqParams.getYear()),
                        driving.month.eq(reqParams.getMonth()),
                        driving.day.eq(reqParams.getDay())
                )
                .fetch();
    }

    private BooleanExpression plateEq(String plate) {
        return plate.equals("total") ? null : driving.plate.eq(plate);
    }

    private BooleanExpression dangerEq(String danger) {
        return danger.equals("totalDan") ? totalDanger() :
                    danger.equals("suddenAcc")? suddenAcc() :
                            danger.equals("suddenDeparture")? suddenDeparture() :
                                    danger.equals("suddenDrop")? suddenDrop() : suddenStop();
    }

    private BooleanExpression totalDanger() {
        return suddenAcc().or(suddenDeparture().or(suddenDrop().or(suddenStop())));
    }

    private BooleanExpression suddenAcc() {
        return driving.suddenAcc.eq(1);
    }

    private BooleanExpression suddenDeparture() {
        return driving.suddenDeparture.eq(1);
    }

    private BooleanExpression suddenDrop() {
        return driving.suddenDrop.eq(1);
    }

    private BooleanExpression suddenStop() {
        return driving.suddenStop.eq(1);
    }

}
