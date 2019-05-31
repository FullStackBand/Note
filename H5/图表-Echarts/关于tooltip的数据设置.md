每个数据都有独立的面板显示独有的内容
```js
myChart.setOption({
	title: {
		text: '项目余额统计图',
		x: 'center',
		y: 'top',
		textAlign: 'top'
	},
	tooltip: {
		trigger: 'item',
		tooltip: {
			formatter: '{b0}: {c0}<br />{b1}: {c1}'
		}
	},
	legend: {
		data: name,
		x: 'center',
		y: 'bottom',
		textAlign: 'center'
	},
	grid: {
		left: '3%',
		right: '4%',
		bottom: '20%',
		containLabel: true
	},
	toolbox: {
		feature: {
			saveAsImage: {}
		}
	},
	xAxis: {
		type: 'category',
		boundaryGap: false,
		data: ["2018年6月", "2018年7月", "2018年8月", "2018年9月", "2018年10月", "2018年11月", "2018年12月"]
	},
	yAxis: {
		type: 'value'
	},
	series: [{
		data: [{
			tooltip: "项目名称:文昌滨海旅游公路昌洒至铺前段工程<br/>到位资金:314<br/>拨付资金:743<br/>余额:-429",
			value: -429
		}, {
			tooltip: "项目名称:文昌滨海旅游公路昌洒至铺前段工程<br/>到位资金:310<br/>拨付资金:334<br/>余额:-24",
			value: -24
		}, {
			tooltip: "项目名称:文昌滨海旅游公路昌洒至铺前段工程<br/>到位资金:6754<br/>拨付资金:748<br/>余额:6006",
			value: 6006
		}, {
			tooltip: "项目名称:文昌滨海旅游公路昌洒至铺前段工程<br/>到位资金:314<br/>拨付资金:1132<br/>余额:-818",
			value: -818
		}, {
			tooltip: "项目名称:文昌滨海旅游公路昌洒至铺前段工程<br/>到位资金:330<br/>拨付资金:13309<br/>余额:-12979",
			value: -12979
		}, {
			tooltip: "项目名称:文昌滨海旅游公路昌洒至铺前段工程<br/>到位资金:1285<br/>拨付资金:359<br/>余额:926",
			value: 926
		}, {
			tooltip: "项目名称:文昌滨海旅游公路昌洒至铺前段工程<br/>到位资金:0<br/>拨付资金:0<br/>余额:0",
			value: 0
		}],
		name: "文昌滨海旅游公路昌洒至铺前段工程",
		type: "line"
	}, {
		data: [{
			tooltip: "项目名称:文昌市文城至铜鼓岭公路改建工程<br/>到位资金:742<br/>拨付资金:767<br/>余额:-25",
			value: -25
		}, {
			tooltip: "项目名称:文昌市文城至铜鼓岭公路改建工程<br/>到位资金:327<br/>拨付资金:741<br/>余额:-414",
			value: -414
		}, {
			tooltip: "项目名称:文昌市文城至铜鼓岭公路改建工程<br/>到位资金:734<br/>拨付资金:749<br/>余额:-15",
			value: -15
		}, {
			tooltip: "项目名称:文昌市文城至铜鼓岭公路改建工程<br/>到位资金:309<br/>拨付资金:6734<br/>余额:-6425",
			value: -6425
		}, {
			tooltip: "项目名称:文昌市文城至铜鼓岭公路改建工程<br/>到位资金:311<br/>拨付资金:316<br/>余额:-5",
			value: -5
		}, {
			tooltip: "项目名称:文昌市文城至铜鼓岭公路改建工程<br/>到位资金:312<br/>拨付资金:763<br/>余额:-451",
			value: -451
		}, {
			tooltip: "项目名称:文昌市文城至铜鼓岭公路改建工程<br/>到位资金:0<br/>拨付资金:0<br/>余额:0",
			value: 0
		}],
		name: "文昌市文城至铜鼓岭公路改建工程",
		type: "line"
	}]
})
```
