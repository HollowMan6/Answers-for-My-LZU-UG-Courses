# -*- coding:utf-8 -*-
# by 'hollowman6' from Lanzhou University(兰州大学)

# 兰州招聘岗位在兰州各个地点的占比
# The proportion of recruitment posts in different locations in Lanzhou

from pyecharts.charts import Bar
from pyecharts import options as opts
import DataWash

Dict=DataWash.washCity()

Addr=[]
Num=[]
for key,value in Dict.items():
    Addr.append(key)
    Num.append(value)
print(Num)
bar = (
    Bar()
    .add_xaxis(Addr)
    .add_yaxis("工作岗位", Num)
    .set_global_opts(title_opts=opts.TitleOpts(title="兰州招聘岗位分布柱状图"))
)
bar.render("./view/jobAddress.html")