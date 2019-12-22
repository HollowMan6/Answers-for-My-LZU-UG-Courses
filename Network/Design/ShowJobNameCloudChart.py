# -*- coding:utf-8 -*-
# by 'hollowman6' from Lanzhou University(兰州大学)

# 兰州工作岗位名称词云图
# WordCloud of Lanzhou's Job Names

import pyecharts.options as opts
from pyecharts.charts import WordCloud
import DataWash


DistinctJobDict=DataWash.washJobName().items()
# print(jobWordDict)
(
    WordCloud()
    .add(series_name="工作岗位", data_pair=DistinctJobDict, word_size_range=[6, 66])
    .set_global_opts(
        title_opts=opts.TitleOpts(
            title="兰州工作岗位", title_textstyle_opts=opts.TextStyleOpts(font_size=23)
        ),
        tooltip_opts=opts.TooltipOpts(is_show=True),
    )
    .render("./view/JobNameWordCloud.html")
)
