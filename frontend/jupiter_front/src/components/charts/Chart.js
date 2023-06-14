import ApexCharts from "apexcharts";
import React, { useEffect, useState } from "react";

const Chart = (probs) => {

    // console.log('chart comp', probs.chartData)

    let [cat, setCat] = useState({});

    useEffect(() => {

        setCat({
            'acc': {
                '5-7': 0,
                '7-9': 0,
                '9-11': 0,
                '11-13': 0,
                '13-15': 0,
                '15-17': 0,
                '17-19': 0,
                '19-21': 0,
                '21-23': 0,
            },
            'dep': {
                '5-7': 0,
                '7-9': 0,
                '9-11': 0,
                '11-13': 0,
                '13-15': 0,
                '15-17': 0,
                '17-19': 0,
                '19-21': 0,
                '21-23': 0,
            },
            'drop': {
                '5-7': 0,
                '7-9': 0,
                '9-11': 0,
                '11-13': 0,
                '13-15': 0,
                '15-17': 0,
                '17-19': 0,
                '19-21': 0,
                '21-23': 0,
            },
            'stop': {
                '5-7': 0,
                '7-9': 0,
                '9-11': 0,
                '11-13': 0,
                '13-15': 0,
                '15-17': 0,
                '17-19': 0,
                '19-21': 0,
                '21-23': 0,
            }
        })

        probs.chartData && probs.chartData.filter((i) => i.위험 === '급가속').map((j) => {
            let time = parseInt(j.시간)
            if (time < 7)
                cat.acc['5-7'] += 1
            else if (time < 9)
                cat.acc['7-9'] += 1
            else if (time < 11)
                cat.acc['9-11'] += 1
            else if (time < 13)
                cat.acc['11-13'] += 1
            else if (time < 15)
                cat.acc['13-15'] += 1
            else if (time < 17)
                cat.acc['15-17'] += 1
            else if (time < 19)
                cat.acc['17-19'] += 1
            else if (time < 21)
                cat.acc['19-21'] += 1
            else
                cat.acc['21-23'] += 1
        })

        probs.chartData && probs.chartData.filter((i) => i.위험 === '급출발').map((j) => {
            let time = parseInt(j.시간)
            if (time < 7)
                cat.dep['5-7'] += 1
            else if (time < 9)
                cat.dep['7-9'] += 1
            else if (time < 11)
                cat.dep['9-11'] += 1
            else if (time < 13)
                cat.dep['11-13'] += 1
            else if (time < 15)
                cat.dep['13-15'] += 1
            else if (time < 17)
                cat.dep['15-17'] += 1
            else if (time < 19)
                cat.dep['17-19'] += 1
            else if (time < 21)
                cat.dep['19-21'] += 1
            else
                cat.dep['21-23'] += 1
        })

        probs.chartData && probs.chartData.filter((i) => i.위험 === '급감속').map((j) => {
            let time = parseInt(j.시간)
            if (time < 7)
                cat.drop['5-7'] += 1
            else if (time < 9)
                cat.drop['7-9'] += 1
            else if (time < 11)
                cat.drop['9-11'] += 1
            else if (time < 13)
                cat.drop['11-13'] += 1
            else if (time < 15)
                cat.drop['13-15'] += 1
            else if (time < 17)
                cat.drop['15-17'] += 1
            else if (time < 19)
                cat.drop['17-19'] += 1
            else if (time < 21)
                cat.drop['19-21'] += 1
            else
                cat.drop['21-23'] += 1
        })

        probs.chartData && probs.chartData.filter((i) => i.위험 === '급정지').map((j) => {
            let time = parseInt(j.시간)
            if (time < 7)
                cat.stop['5-7'] += 1
            else if (time < 9)
                cat.stop['7-9'] += 1
            else if (time < 11)
                cat.stop['9-11'] += 1
            else if (time < 13)
                cat.stop['11-13'] += 1
            else if (time < 15)
                cat.stop['13-15'] += 1
            else if (time < 17)
                cat.stop['15-17'] += 1
            else if (time < 19)
                cat.stop['17-19'] += 1
            else if (time < 21)
                cat.stop['19-21'] += 1
            else
                cat.stop['21-23'] += 1
        })

        // console.log('cat', cat.acc)
        // setCa(cat)
        // console.log('cat', cat.acc)
        // console.log('cat length', Object.keys(cat).length)

        let options = null;

        if (Object.keys(cat).length === 0) {

            options = {
                chart: {
                    type: 'bar',
                    height: '62%',
                    width: '100%',
                    stacked: true,
                    fontFamily: "D2Coding",
                },
                series: [{
                    name: '급가속',
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0]
                }, {
                    name: '급출발',
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0]
                }, {
                    name: '급감속',
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0]
                }, {
                    name: '급정지',
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0]
                }],
                xaxis: {
                    categories: ['5-7', '7-9', '9-11', '11-13', '13-15', '15-17', '17-19', '19-21', '21-23']
                },
                yaxis: {
                    max: 10,
                    tickAmount: 5,
                    labels: {
                        formatter: function (val) {
                            return val.toFixed(0);
                        }
                    }
                }
            }

        } else {

            options = {
                chart: {
                    type: 'bar',
                    height: '62%',
                    width: '100%',
                    stacked: true,
                    fontFamily: "D2Coding",
                },
                series: [{
                    name: '급가속',
                    data: Object.values(cat.acc)
                }, {
                    name: '급출발',
                    data: Object.values(cat.dep)
                }, {
                    name: '급감속',
                    data: Object.values(cat.drop)
                }, {
                    name: '급정지',
                    data: Object.values(cat.stop)
                }],
                xaxis: {
                    categories: ['5-7', '7-9', '9-11', '11-13', '13-15', '15-17', '17-19', '19-21', '21-23']
                },

                yaxis: {
                    max: function (max) {
                        if (max <= 5)
                            return 5;
                        else
                            return max + 5;
                    },
                    labels: {
                        formatter: function (val) {
                            return val.toFixed(0);
                        }
                    }
                }
            }
        }

        let chart = new ApexCharts(document.getElementById('chart'), options);
        // console.log('chart', chart);
        chart.render();

        return () => {
            chart.destroy();
        };


    }, [probs.chartData])



    return (
        <>
            <div id="chart" className="chart">
            </div>
        </>
    )

}

export default Chart;