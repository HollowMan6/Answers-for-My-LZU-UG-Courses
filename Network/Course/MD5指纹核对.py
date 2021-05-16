import hashlib

def out_md5(src):
    # 简单封装
    m = hashlib.md5()
    m.update(src)
    return m.hexdigest()

print('-两个文件MD5值的比较-\n')
f1 = input('请输入文件1的路径或文件名：')
f2 = input('请输入文件2的路径或文件名：')
print()
with open(f1, 'rb') as f:
    src = f.read()
    m1 = out_md5(src)
    print('文件1：'+m1)
with open(f2, 'rb') as f:
    src = f.read()
    m2 = out_md5(src)
    print('文件2：'+m2)
print('\n-结果-')
if m1 == m2:
    print("两个文件为相同文件")
else:
    print("两个文件为不同文件")