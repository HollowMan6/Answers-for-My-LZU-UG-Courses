# -*- coding:utf-8 -*-
# by 'hollowman6' from Lanzhou University(兰州大学)

# 兰州工作岗位的学历要求饼图
# Lanzhou's pie chart of academic requirements for jobs

import pyecharts.options as opts
from pyecharts.charts import Pie
import DataWash
EduLevelList,EduLevelNumList=DataWash.washEduLevel()

inner_x_data = EduLevelList[:3]
inner_y_data = EduLevelNumList[:3]
inner_data_pair = [list(z) for z in zip(inner_x_data, inner_y_data)]

outer_x_data = EduLevelList
outer_y_data = EduLevelNumList
outer_data_pair = [list(z) for z in zip(outer_x_data, outer_y_data)]

(
    Pie(init_opts=opts.InitOpts(width="1600px", height="800px"))
    .add(
        series_name="学历要求",
        data_pair=inner_data_pair,
        radius=[0, "30%"],
        label_opts=opts.LabelOpts(position="inner"),
    )
    .add(
        series_name="学历要求",
        radius=["40%", "55%"],
        data_pair=outer_data_pair,
        label_opts=opts.LabelOpts(
            position="outside",
            formatter="{a|{a}}{abg|}\n{hr|}\n {b|{b}: }{c}  {per|{d}%}  ",
            background_color="#eee",
            border_color="#aaa",
            border_width=1,
            border_radius=4,
            rich={
                "a": {"color": "#999", "lineHeight": 22, "align": "center"},
                "abg": {
                    "backgroundColor": "#e3e3e3",
                    "width": "100%",
                    "align": "right",
                    "height": 22,
                    "borderRadius": [4, 4, 0, 0],
                },
                "hr": {
                    "borderColor": "#aaa",
                    "width": "100%",
                    "borderWidth": 0.5,
                    "height": 0,
                },
                "b": {"fontSize": 16, "lineHeight": 33},
                "per": {
                    "color": "#eee",
                    "backgroundColor": "#334455",
                    "padding": [2, 4],
                    "borderRadius": 2,
                },
            },
        ),
    )
    .set_global_opts(
        legend_opts=opts.LegendOpts(pos_left="left", orient="vertical"),
        title_opts=opts.TitleOpts(title="兰州工作岗位的学历要求饼图")
        )
    .set_series_opts(
        tooltip_opts=opts.TooltipOpts(
            trigger="item", formatter="{a} <br/>{b}: {c} ({d}%)"
        )
    )
    .render("./view/EduLevelPie.html")
)