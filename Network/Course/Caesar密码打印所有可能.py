lalph="abcdefghijklmnopqrstuvwxyz"
halph="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
cyper=input("请输入要解密的字符串：")
decyper=""
for i in range(1,26):
    for j in range(len(cyper)):
        if cyper[j].isalpha():
            if cyper[j].islower():
                decyper+=lalph[(lalph.index(cyper[j])+i)%26]
            else:
                decyper+=halph[(halph.index(cyper[j])+i)%26]
        else:
            decyper+=cyper[j]
    print("正向移动"+str(i)+"位："+decyper)
    decyper=""