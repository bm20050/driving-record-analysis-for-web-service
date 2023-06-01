import ApexCharts from "apexcharts";
import React, { useEffect, useState } from "react";

const AccChart = (probs) => {

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

        let options = null;

        if (Object.keys(cat).length === 0) {

            options = {
                chart: {
                    type: 'line',
                    height: '100%',
                    width: '100%',
                    fontFamily: "D2Coding",
                    toolbar: {
                        show: false,
                    },
                },
                series: [{
                    name: '급가속',
                    data: [0,0,0,0,0,0,0,0,0]
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
                }, 
                stroke: {
                    width: 2,
                    colors: ['#26A0FC']
                },
            }

        } else {
            options = {
                chart: {
                    type: 'line',
                    height: '100%',
                    width: '100%',
                    fontFamily: "D2Coding",
                    toolbar: {
                        show: false,
                    },
                },
                series: [{
                    name: '급가속',
                    data: Object.values(cat.acc)
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
                }, 
                stroke: {
                    width: 2,
                    colors: ['#26A0FC']
                },
            }
        }

        let chart = new ApexCharts(document.getElementById('gragh1'), options);

        chart.render();

        return () => {
            chart.destroy();
        };


    }, [probs.chartData])

    return(
        
        <div id="gragh1">
        </div>
        
    )
}

export default AccChart;