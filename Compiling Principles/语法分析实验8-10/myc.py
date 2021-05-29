#!/usr/bin/python3
# By Songlin Jiang, 320180901941


import sys
import os
import lexer
import cparser

if __name__ == "__main__":
    # When having no argument
    if len(sys.argv) == 1:
        print("Error, no input directory")
    # When having 2 or more arguments
    elif len(sys.argv) > 2:
        print("Error, too many arguments, please only type your C program directory")
    else:
        # Check whether the folder typed exists
        if not os.path.exists(sys.argv[1]):
            print("Error, please make sure your typed C program directory exists")
        # Check whether the name typed is a folder
        elif os.path.isfile(sys.argv[1]):
            print("Error, please type your C program directory instead of file name")
        else:
            filename = os.listdir()
            # Record the c file number so that later on it can avoid no c files in the folder
            count = 0
            for fpathe, dirs, fs in os.walk(sys.argv[1]):
                for f in fs:
                    file = os.path.join(fpathe, f)
                    if os.path.splitext(f)[1] == ".c":
                        count += 1
                        with open(file) as source:
                            # Read Source Code
                            sourcecode = []
                            temp = source.readline()
                            while temp:
                                sourcecode.append(temp.replace("\n", ""))
                                temp = source.readline()
                            # Compile begin
                            print("\nCompiling "+file)
                            tokens = lexer.Token(sourcecode)
                            try:
                                destcode = ""
                                while True:
                                    token = tokens.GetNextToken()
                                    if token[1] == "EOF":
                                        break
                                    else:
                                        destcode += str(token)+"\n"
                                tokens.line = 0
                                tokens.pointer = 0
                                cparser = cparser.CParser(tokens)
                                cparser.block()
                                print("Compilation success")
                                with open(os.path.join(fpathe, os.path.splitext(f)[0]+".txt"), 'w') as dest:
                                    dest.write(destcode)
                                with open(os.path.join(fpathe, os.path.splitext(f)[0]+"-gen.txt"), 'w') as dest:
                                    dest.write(cparser.generated)
                            # Give error infomation
                            except Exception as err:
                                print(err.args[0]+" error, line "+str(err.args[1]) +
                                      ', close to "'+str(err.args[2])+'", '+str(err.args[3]))
                            # Write Generated Code
            if count == 0:
                print("Error, unable to find any c source files in your typed C program directory, "
                      "please make sure your source code files have .c extension")
            # Compiling finish
            else:
                print("\nCompilation Complete!")
