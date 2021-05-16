# -*- coding:utf-8 -*-
# by 'hollowman6' from Lanzhou University(兰州大学)

# 兰州工作岗位所在公司规模漏斗图
# Lanzhou's Company size funnel plot

from pyecharts import options as opts
from pyecharts.charts import Funnel
import DataWash

CompanySizeList,CompanySizeNumList=DataWash.washCompany_size()

data = [[CompanySizeList[i], CompanySizeNumList[i]] for i in range(len(CompanySizeList))]

(
    Funnel(init_opts=opts.InitOpts(width="1200px", height="600px"))
    .add(
        series_name="",
        data_pair=data,
        gap=2,
        tooltip_opts=opts.TooltipOpts(trigger="item", formatter="{a} <br/>{b} : {c}%"),
        label_opts=opts.LabelOpts(is_show=True, position="inside"),
        itemstyle_opts=opts.ItemStyleOpts(border_color="#fff", border_width=1),
    )
    .set_global_opts(title_opts=opts.TitleOpts(title="兰州工作岗位所在公司规模漏斗图"))
    .render("./view/CompanySizeFunnel.html")
)
