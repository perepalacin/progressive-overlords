export function drawLineChart (chartValues) {
    const chartContainer = document.getElementById('chart-container');
        if (chartContainer) {
            const myChart = echarts.init(chartContainer);

            const rawData = chartValues;

            const formatDate = (dateString) => {
                const date = new Date(dateString);
                const day = date.getDate().toString().padStart(2, '0');
                const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Month is 0-indexed
                const year = date.getFullYear();
                return `${day}/${month}/${year}`;
            };

            const seriesData = rawData.map(item => [
                item.date,
                item.erp
            ]);

            const options = {
                backgroundColor: '#add8e6',
                tooltip: {
                    trigger: 'axis',
                    formatter: function (params) {
                        params = params[0];
                        const date = new Date(params.value[0]);
                        const formattedDate = formatDate(date);
                        const erpValue = params.value[1].toFixed(2);
                        return `Date: ${formattedDate}<br/>ERP: ${erpValue} kg`;
                    },
                    axisPointer: {
                        animation: false
                    }
                },
                xAxis: {
                    type: 'time',
                    boundaryGap: false,
                    axisLabel: {
                        formatter: function (value) {
                            const date = new Date(value);
                            return formatDate(date);
                        }
                    },
                     axisLine: {
                        lineStyle: {
                            color: '#333'
                        }
                    },
                    axisTick: {
                        alignWithLabel: true
                    }
                },
                yAxis: {
                    type: 'value',
                    name: 'kilograms',
                     axisLine: {
                        lineStyle: {
                            color: '#333'
                        }
                    },
                    axisLabel: {
                         formatter: '{value} kg'
                    }
                },
                series: [{
                    name: 'ERP',
                    type: 'line',
                    showSymbol: true,
                    itemStyle: {
                        color: 'red'
                    },
                    data: seriesData
                }]
            };

            myChart.setOption(options);

            window.addEventListener('resize', function() {
                myChart.resize();
            });
        } else {
            console.error("Chart container element not found!");
        }
}