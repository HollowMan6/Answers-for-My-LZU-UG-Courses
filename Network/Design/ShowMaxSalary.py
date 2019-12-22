# -*- coding:utf-8 -*-
# by 'hollowman6' from Lanzhou University(兰州大学)

# 兰州工作岗位的最高薪水折线图
# The Breakdown Map of Lanzhou's Maximum Salary for Lanzhou Jobs

import pyecharts.options as opts
from pyecharts.charts import Line
import DataWash
import random
jobNameList,MaxSalaryList=DataWash.washMaxSalary()
showNum=len(jobNameList)

list=[]
for i in range(len(jobNameList)):
    list.append((jobNameList[i],MaxSalaryList[i]))
showJob=[]
for i in range(showNum):
    showJob.append(random.choice(list))
showJobnameArr=[]
showSalArr=[]
for (job,sal) in showJob:
    showJobnameArr.append(job)
    showSalArr.append(sal)
showlist=[str(i) for i in range(1,showNum+1)]

(
    Line()
    .set_global_opts(
        tooltip_opts=opts.TooltipOpts(is_show=False),
        xaxis_opts=opts.AxisOpts(type_="category"),
        yaxis_opts=opts.AxisOpts(
            type_="value",
            axistick_opts=opts.AxisTickOpts(is_show=True),
            splitline_opts=opts.SplitLineOpts(is_show=True),
        ),
        title_opts=opts.TitleOpts(title="兰州最高薪水折线图")
    )
    .add_xaxis(xaxis_data=showlist)
    .add_yaxis(
        series_name="",
        y_axis=showSalArr,
        symbol="emptyCircle",
        is_symbol_show=True,
        is_smooth=True,
        label_opts=opts.LabelOpts(is_show=False),
    )
    .render("./view/maxSalaryLineChart.html")
)