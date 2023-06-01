import ApexCharts from "apexcharts";
import React, { useEffect, useState } from "react";

const DepartChart = (probs) => {

    let [cat, setCat] = useState({});

    useEffect(() => {

        setCat({
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
                    name: '급출발',
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
                    colors: ['#26E7A6']
                },
                markers: {
                    colors: ['#26E7A6']
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
                    name: '급출발',
                    data: Object.values(cat.dep)
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
                    colors: ['#26E7A6']
                },
                markers: {
                    colors: ['#26E7A6']
                },
            }
        }

        let chart = new ApexCharts(document.getElementById('gragh2'), options);

        chart.render();

        return () => {
            chart.destroy();
        };


    }, [probs.chartData])

    return(
        
        <div id="gragh2">
        </div>
        
    )
}

export default DepartChart;