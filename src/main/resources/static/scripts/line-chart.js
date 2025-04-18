const getPreferredTextColor = () => {
    if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
        return '#ffffff';
    } else {
        return '#333333';
    }
};

const getPreferredLinesColor = () => {
    if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
        return '#2b2b2b';
    } else {
        return '#e8e8e8';
    }
};

const getPreferredTooltipBgColor = () => {
    if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
        return 'rgba(50, 50, 50, 0.7)';
    } else {
        return 'rgba(255, 255, 255, 0.9)';
    }
};

export function drawLineChart (chartValues) {
    const chartContainer = document.getElementById('chart-container');
        if (chartContainer) {
            const myChart = echarts.init(chartContainer);

            const rawData = chartValues;

            const textColor = getPreferredTextColor();
            const linesColor = getPreferredLinesColor();
            const tooltipBgColor = getPreferredTooltipBgColor();

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
                title: {
                    text: 'Your Progression',
                    left: 'center',
                    textStyle: {
                        color: textColor
                    }
                },
                backgroundColor: 'transparent',
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
                    },
                    textStyle: {
                        fontSize: 16,
                        color: textColor
                    },
                    backgroundColor: tooltipBgColor
                },
                xAxis: {
                    type: 'time',
                    boundaryGap: false,
                    splitNumber: 4,
                    axisLabel: {
                        formatter: function (value) {
                            const date = new Date(value);
                            return formatDate(date);
                        },
                        fontSize: 16,
                        color: textColor
                    },
                    axisLine: {
                        lineStyle: {
                            color: textColor
                        }
                    },
                    axisTick: {
                        alignWithLabel: true,
                        lineStyle: {
                            color: textColor
                        }
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: [linesColor],
//                            type: 'dashed'
                        }
                    }
                },
                yAxis: {
                    type: 'value',
                    splitNumber: 4,
                    axisLabel: {
                        formatter: '{value} kg',
                        fontSize: 14,
                        color: textColor
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: [linesColor],
//                            type: 'dashed'
                        }
                    }
                },
                series: [{
                name: 'ERP',
                    type: 'line',
                    showSymbol: true,
                    itemStyle: {
                        color: '#C4554D'
                    },
                    data: seriesData,
                }],
                grid: {
                    left: '2%',
                    right: '2%',
                    bottom: '5%',
                    top: '8%',
                    containLabel: true
                }
            };

            myChart.setOption(options);

            window.addEventListener('resize', function() {
                myChart.resize();
            });
        } else {
            console.error("Chart container element not found!");
        }
}