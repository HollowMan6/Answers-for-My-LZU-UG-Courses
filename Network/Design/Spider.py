# -*- coding:utf-8 -*-
# by 'hollowman6' from Lanzhou University(兰州大学)

# 爬虫主程序 Crawler master program

import requests

# 使用urlencode将key-value这样的键值对转换成我们想要的格式，返回的是a=1&b=2这样的字符串。
# Use Urlencode to convert key-value pairs into the format we want, and return strings such as a = 1 & B = 2.
from urllib.parse import urlencode
from requests.exceptions import RequestException

# 将json文件保存到本地XXX.json，再次读取将json字符串转换为对应的python数据类型（load 方法），主要是{}对象转dict,[]数组转list 
# Save the JSON file to the local XX.json and read again to convert the JSON string to the corresponding Python data type (load method), mainly {} object to dict, [] array to list.
import json
import sqlite3

# sqllite3的数据库位置
# The database location of sqllite3
conn = sqlite3.connect('jobData.db')

# get方式提交的参数字典
# Parameter dictionary submitted by get mode
import re
param={
    'start':0,
    'pageSize':60,
    'cityId':864,
    'workExperience':-1,
    'education':-1,
    'companyType': -1,
    'employmentType': -1,
    'jobWelfareTag': -1,
    'kt': 3,
    'lastUrlQuery': {"p":4,"pageSize":"60","jl":"681","kt":"3"}
}
# http协议中request请求的请求头信息，数值可以使用本地浏览器开发者工具（F12）查看并修改 
# Request header information of request request request in http protocol. The value can be viewed and modified using the Local Browser Developer Tool (F12)
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36',
    'Host': 'fe-api.zhaopin.com',
    'Referer': 'https://sou.zhaopin.com/?p=1&pageSize=60&jl=681&kt=3',
    'Accept': 'application/json, text/plain, */*',
    'Accept-Encoding': 'gzip, deflate',
    'Accept-Language': 'zh-CN,zh;q=0.9',
    'Origin': 'https://sou.zhaopin.com',
}

def getPage(city='',pageNo=1):
    param['start']=60*(pageNo-1)
    tempDict={"p":4,"pageSize":"60","jl":"864","kt":"3"}
    tempDict['p']=pageNo
    url = 'https://fe-api.zhaopin.com/c/i/sou?' + urlencode(param)
    try:
        # 获取网页内容，返回html数据
        # Get the content of the web page and return HTML data
        response = requests.get(url, headers=headers)
        data = response.content.decode('utf-8')
        filename='智联_'+city+'_第'+str(pageNo)+'页_数据.json'
        with open(filename,'w',encoding='utf-8')as f:
            f.write(data)
        if response.status_code == 200:
            return data
        return None
    except RequestException as e:
        return None
def savToSqlite(keyword,pageNo):
    for i in range(1,pageNo+1):
        filename='智联_兰州_第'+str(i)+'页_数据.json'
        with open(filename,'r',encoding='utf-8') as f:
            data=f.read()
        dataJson = json.loads(data)
        dataList=dataJson['data']['results']
        for record in dataList:
            number=keyword+'_'+record['number']
            jobType = ''.join(re.findall(r"{'name': '(.+?)'}", str(record['jobType']['items'])))
            companyName=record['company']['name']
            company_size=record['company']['size']['name']
            company_type=record['company']['type']['name']
            workingExp=record['workingExp']['name']
            eduLevel = record['eduLevel']['name']
            salary=record['salary']
            emplType= record['emplType']
            jobName=record['jobName']
            city=record['city']['display']
            # 列表字符串 List string
            welfare=','.join(record['welfare'])
            timeState=record['timeState']
            insertSql='insert into recruitment(number,jobType,companyName,company_size,company_type,workingExp,' \
                    'eduLevel,salary,emplType,jobName,city,welfare,timeState,keyword) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)'
            conn.execute(insertSql,(number,jobType,companyName,company_size,company_type,workingExp,eduLevel,salary,emplType,jobName,city,welfare,timeState,keyword))
            conn.commit()

if __name__ == '__main__':
    totalPage=12
    # 下载json文件 Download JSON file
    for pageNo in range(1,totalPage+1):
        html=getPage('兰州',pageNo)
    # 保存到数据库 Save to database
    savToSqlite('兰州',totalPage)