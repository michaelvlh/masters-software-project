/*
 * @Author: Jipu Li 
 * @Date: 2022-03-28 17:56:01 
 * @Last Modified by: Jipu Li
 * @Last Modified time: 2022-05-10 00:24:23
 */

var app = new Vue({
  el: '#app',
  data() {
    return {
      title: 'Food For All',
      loginState: loginState,
      charityName: charityName,
      tagActives: {
        projects: false,
        graphs: true,
        settings: false,
        profiles: false,
      },
      totlaProjects: dashboardData.numOfProjects,
      completedProjects: dashboardData.numOfCompletedProjects,
      totalRevenue: dashboardData.totalRevenue,
    }
  },
  methods: {
  }
})

function ProcessPieChartData(dashboardData) {
  var projects = dashboardData.revenueList
  var dataList = []
  projects.forEach(project => {
    var data = { value: project.currentRevenue, name: project.title }
    dataList.push(data);
  });

  return dataList
}

function RenderPieCharts(data) {
  console.log("Pie", data)
  // pie chart
  var pieDom = document.getElementById("pieChart");
  var myPieChart = echarts.init(pieDom);
  // var app = {};
  var option;

  var pieData = data

  option = {
    animation:false,
    title: {
      text: 'Projects Income',
      subtext: 'Fake Data',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    series: [
      {
        name: 'income calculate by dollar',
        type: 'pie',
        radius: '50%',
        data: pieData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };

  if (option && typeof option === 'object') {
    myPieChart.setOption(option);
  }
}

async function DrawPieChart() {
  var data = await ProcessPieChartData(dashboardData)
  await RenderPieCharts(data)
  var lineData = await ProcessPieChartData(dashboardData)
  linexAxis = []
  lineyAxis = []

  lineData.forEach(data => {
    linexAxis.push(data.name)
    lineyAxis.push(data.value)
  });

  await RenderLineChart(linexAxis, lineyAxis)

}

async function DrawLineChart() {
  var lineData = await ProcessPieChartData(dashboardData)
  linexAxis = []
  lineyAxis = []

  lineData.forEach(data => {
    linexAxis.push(data.name)
    lineyAxis.push(data.value)
  });

  await RenderLineChart(linexAxis, lineyAxis)
}

function ProcessLineChartData() {

  var lineData = []

  return lineData;
}



function RenderLineChart(x, y) {
  // graph settings
  var lineDom = document.getElementById("lineChart");
  var myLineChart = echarts.init(lineDom);
  var XAxis = x
  var YAxis = y

  // var app = {};
  var option;
  option = {
    animation:false,
    title: {
      text: 'Projects Income'
    },
    xAxis: {
      type: 'category',
      data: XAxis
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: YAxis,
        type: 'line'
      }
    ]
  };
  if (option && typeof option === 'object') {
    myLineChart.setOption(option);
  }
}

function printDiv(){
  window.print()
}

const exportBtn = document.querySelector('#export')
exportBtn.addEventListener('click', (e)=>{
  e.preventDefault
  printDiv('dashboard')
})


function init() {
  console.log(("dashBoard: ", dashboardData))
  DrawPieChart();
  DrawLineChart();
}
window.onload = init




