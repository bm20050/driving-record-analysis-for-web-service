import ApexCharts from "apexcharts";
import React, { useEffect, useState } from "react";

const DropChart = (probs) => {

    let [cat, setCat] = useState({});

    useEffect(() => {

        setCat({
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
                    name: '급감속',
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
                    width: 1,
                    colors: ['#FEBC3B']
                },
                markers: {
                    colors: ['#FEBC3B']
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
                    name: '급감속',
                    data: Object.values(cat.drop)
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
                    colors: ['#FEBC3B']
                },
                markers: {
                    colors: ['#FEBC3B']
                },
            }
        }

        let chart = new ApexCharts(document.getElementById('gragh3'), options);

        chart.render();

        return () => {
            chart.destroy();
        };


    }, [probs.chartData])

    return(
        
        <div id="gragh3">
        </div>
        
    )
}

export default DropChart;