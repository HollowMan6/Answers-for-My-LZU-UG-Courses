# -*- coding:utf-8 -*-
# by 'hollowman6' from Lanzhou University(兰州大学)

# 数据清洗与分类处理函数 
# Data Washing and Classification Processing Functions

import sqlite3
from collections import Counter
conn = sqlite3.connect('./data/jobData.db')

def washCity():
    """
    比较兰州的招聘岗位在兰州各个区的占比
    Comparing the proportion of recruitment posts in each district of Lanzhou
    """
    CityCursor = conn.execute("select city from recruitment")
    CityCounter = Counter(CityCursor)
    TempList=CityCounter.most_common()

    Dict={}
    for ((key,),number) in TempList:
        Dict[key]=number
    return Dict

def washMinSalary():
    """
    最低薪水处理
    Minimum Salary Processing
    """
    SalaryCursor = conn.execute("select salary,jobName from recruitment")
    realSalaryList=[]
    jobNameList=[]
    for salaryRow in SalaryCursor:
        find=False
        salaryStr=salaryRow[0]
        if '薪资面议' in salaryStr:
            break
        if not find:
            realSalaryList.append(salaryStr)
            jobNameList.append(salaryRow[1])
    MinSalaryList=[]
    for salaryStr in realSalaryList:
        index=salaryStr.find('K')
        MinSalaryList.append(float(salaryStr[0:index]))
    return jobNameList,MinSalaryList

def washMaxSalary():
    """
    最高薪水处理
    Maximum Salary Processing
    """
    SalaryCursor = conn.execute("select salary,jobName from recruitment")
    realSalaryList=[]
    jobNameList=[]
    for salaryRow in SalaryCursor:
        find=False
        salaryStr=salaryRow[0]
        if '薪资面议' in salaryStr:
            break
        if not find:
            realSalaryList.append(salaryStr)
            jobNameList.append(salaryRow[1])
    MaxSalaryList=[]
    for salaryStr in realSalaryList:
        index=salaryStr.find('-')
        MaxSalaryList.append(float(salaryStr[index+1:-1]))
    return jobNameList,MaxSalaryList

def washEduLevel():
    """
    学历处理
    Education Level processing
    """
    EduLevelCursor = conn.execute("select eduLevel from recruitment")
    WorkCounter=Counter(EduLevelCursor)
    PieList=WorkCounter.most_common()
    EduLevelList=[edulevel for ((edulevel),num) in PieList]
    EduLevelNumList = [num for ((edulevel), num) in PieList]
    return EduLevelList,EduLevelNumList

def washWorkingExp():
    """
    工作经验处理
    Working Experience Processing
    """
    EduLeworkingExpvelCursor = conn.execute("select workingExp from recruitment")
    WorkCounter = Counter(EduLeworkingExpvelCursor)
    PieList = WorkCounter.most_common()
    WorkingExpList = [workingExp for ((workingExp,), num) in PieList]
    WorkingExpNumList = [num for ((workingExp,), num) in PieList]
    return WorkingExpList, WorkingExpNumList

def washJobName():
    """
    岗位处理
    Job Name processing
    """
    JobNameCursor = conn.execute("select jobName from recruitment")
    JobNameCounter = Counter(JobNameCursor)
    jobNameList = JobNameCounter.most_common()
    DistinctJobDict = {}
    for ((a,), b) in jobNameList:
        key = str.upper(a)
        if (key in DistinctJobDict.keys()):
            DistinctJobDict[key] += b
        else:
            DistinctJobDict[key] = b
    return DistinctJobDict

def washCompany_size():
    """
    公司大小处理
    Company Size Processing
    """
    CompanySizeCursor = conn.execute("select company_size from recruitment")
    WorkCounter=Counter(CompanySizeCursor)
    FunnelList=WorkCounter.most_common()
    CompanySizeList=[funnel for ((funnel),num) in FunnelList]
    CompanySize=[]
    for i in CompanySizeList:
        for j in i:
            CompanySize.append(j)
    CompanySizeNumList = [num for ((funnel), num) in FunnelList]
    return CompanySize,CompanySizeNumList

def washCompanyType():
    """
    公司类型处理
    Company Type Processing
    """
    CompanyTypeCursor = conn.execute("select company_type from recruitment")
    WorkCounter=Counter(CompanyTypeCursor)
    PieList=WorkCounter.most_common()
    CompanyTypeList=[companyType for ((companyType),num) in PieList]
    CompanyTypeNumList = [num for ((companyType), num) in PieList]
    return CompanyTypeList,CompanyTypeNumList