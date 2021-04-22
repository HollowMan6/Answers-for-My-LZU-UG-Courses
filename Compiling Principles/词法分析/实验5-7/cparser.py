#!/usr/bin/python3
# By Songlin Jiang, 320180901941


class CParser:

    token = None
    XMLTree = '<?xml version="1.0" encoding="UTF-8"?>\n'

    def __init__(self, token):
        self.token = token

    def XMLEscape(self, token):
        return str(token).replace("&","&amp;").replace("'", "&apos;").replace("<", "&lt;").replace(">", "&gt;")

    def block(self):
        nextToken=self.token.PeekNextToken()
        self.XMLTree += "<block>\n"
        while True:
            if nextToken[0]=='struct':
                self.structDeclar()
                nextToken=self.token.PeekNextToken()
            elif nextToken[1]=='Type' or nextToken[0]=='void':
                self.functionDeclar()
                nextToken=self.token.PeekNextToken()
            elif nextToken[1]!='EOF':
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"Unidentified token.")
            else:
                self.XMLTree += "</block>\n"
                break


    def structDeclar(self):
        nextToken=self.token.GetNextToken()
        self.XMLTree += "<structDeclar>\n"
        if nextToken[0]=="struct":
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            nextToken=self.token.GetNextToken()
            if nextToken[1]=="Identifier":
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                if nextToken[0]=="{":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.PeekNextToken()
                    while nextToken[1]=='Type':
                        self.varDeclarStatement()
                        nextToken=self.token.PeekNextToken()
                    nextToken=self.token.GetNextToken()
                    if nextToken[0]=="}":
                        self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    else:
                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"an identifier expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'struct' expected at this area")
        self.XMLTree += "</structDeclar>\n"

    def functionDeclar(self):
        nextToken=self.token.GetNextToken()
        self.XMLTree += "<functionDeclar>\n"
        if nextToken[0]=="void" or nextToken[1]=="Type" or nextToken[1]=="Identifier":
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            nextToken=self.token.GetNextToken()
            if nextToken[1]=="Identifier":
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                if nextToken[0]=="(":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    self.paramList()
                    nextToken=self.token.GetNextToken()
                    if nextToken[0]==")":
                        self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                        nextToken=self.token.GetNextToken()
                        if nextToken[0]=="{":
                            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                            while self.statementTest():
                                self.statement()
                            nextToken=self.token.GetNextToken()
                            if nextToken[0]=="}":
                                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                            else:
                                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
                        else:
                            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area") 
                    else:
                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"')' expected at this area")
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'(' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"an identifier expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a type at this area")
        self.XMLTree += "</functionDeclar>\n"

    # using to test whether it's possibly a statement by checking it's head  
    def statementTest(self):
        nextToken=self.token.PeekNextToken()
        if nextToken[0]=='if' or nextToken[1]=='Type' or nextToken[0]=='while' or nextToken[0]=='do' or nextToken[0]=='return' or nextToken[1]=='Identifier':
            return True
        else:
            return False

    def statement(self):
        nextToken=self.token.PeekNextToken()
        self.XMLTree += "<statement>\n"
        if nextToken[0]=='if':
            self.ifStatement()
        elif nextToken[1]=='Type':
            self.varDeclarStatement()
        elif nextToken[0]=='while':
            self.whileStatement()
        elif nextToken[0]=='do':
            self.doStatement()
        elif nextToken[0]=='return':
            self.returnStatement()
        elif nextToken[1]=='Identifier':
            self.assignStatement()
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a statement at this area")
        self.XMLTree += "</statement>\n"

    def paramList(self):
        nextToken=self.token.PeekNextToken()
        self.XMLTree += "<paramList>\n"
        if nextToken[1]=="Identifier":
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            nextToken=self.token.GetNextToken()
            nextToken=self.token.PeekNextToken()
            if nextToken[0]=="[":
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]=="]":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.PeekNextToken()
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"']' expected at this area")
            while nextToken[0]==',':
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                nextToken=self.token.PeekNextToken()
                if nextToken[1]=="Identifier":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.GetNextToken()
                    nextToken=self.token.PeekNextToken()
                    if nextToken[0]=="[":
                        self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                        nextToken=self.token.GetNextToken()
                        self.expression()
                        nextToken=self.token.GetNextToken()
                        if nextToken[0]=="]":
                            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                            nextToken=self.token.PeekNextToken()
                        else:
                            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"']' expected at this area")
        self.XMLTree += "</paramList>\n"


    def varDeclarStatement(self):
        nextToken=self.token.GetNextToken()
        self.XMLTree += "<varDeclarStatement>\n"
        if nextToken[0]=="void" or nextToken[1]=="Type" or nextToken[1]=="Identifier":
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            nextToken=self.token.PeekNextToken()
            if nextToken[1]=="Identifier":
                self.paramList()
                nextToken=self.token.PeekNextToken()
                if nextToken[0]=="=":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.GetNextToken()
                    self.expression()
                    nextToken=self.token.GetNextToken()
                    if nextToken[0]==";":
                        self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    else:
                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"';' expected at this area")
                elif nextToken[0]==";":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.GetNextToken()
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"';' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"an identifier expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"a Type is expected at this area")
        self.XMLTree += "</varDeclarStatement>\n"

    def assignStatement(self):
        self.XMLTree += "<assignStatement>\n"
        nextToken=self.token.GetNextToken()
        if nextToken[1]=="Identifier":
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            nextToken=self.token.PeekNextToken()
            if nextToken[0]=="[":
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]=="]":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.PeekNextToken()
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"']' expected at this area")
            if nextToken[0]=="=":
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]==";":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"';' expected at this area")
            elif nextToken[0]==";":
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"';' expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"an identifier expected at this area")
        self.XMLTree += "</assignStatement>\n"

    def ifStatement(self):
        nextToken=self.token.GetNextToken()
        self.XMLTree += "<ifStatement>\n"
        if nextToken[0]=="if":
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            nextToken=self.token.GetNextToken()
            if nextToken[0]=="(":
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]==")":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.GetNextToken()
                    if nextToken[0]=="{":
                        self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                        while self.statementTest():
                            self.statement()
                        nextToken=self.token.GetNextToken()
                        if nextToken[0]=="}":
                            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                            nextToken=self.token.PeekNextToken()
                            if nextToken[0]=="else":
                                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                                nextToken=self.token.GetNextToken()
                                nextToken=self.token.GetNextToken()
                                if nextToken[0]=="{":
                                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                                    while self.statementTest():
                                        self.statement()
                                    nextToken=self.token.GetNextToken()
                                    if nextToken[0]=="}":
                                        self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                                    else:
                                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
                                else:
                                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area")
                        else:
                            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
                    else:
                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area")
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"')' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'(' expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'if' expected at this area")
        self.XMLTree += "</ifStatement>\n"

    def whileStatement(self):
        self.XMLTree += "<whileStatement>\n"
        nextToken=self.token.GetNextToken()
        if nextToken[0]=="while":
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            nextToken=self.token.GetNextToken()
            if nextToken[0]=="(":
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]==")":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.GetNextToken()
                    if nextToken[0]=="{":
                        self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                        while self.statementTest():
                            self.statement()
                        nextToken=self.token.GetNextToken()
                        if nextToken[0]=="}":
                            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                        else:
                            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
                    else:
                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area")
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"')' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'(' expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'while' expected at this area")
        self.XMLTree += "</whileStatement>\n"

    def doStatement(self):
        nextToken=self.token.GetNextToken()
        self.XMLTree += "<doStatement>\n"
        if nextToken[0]=="do":
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            nextToken=self.token.GetNextToken()
            if nextToken[0]=="{":
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                while self.statementTest():
                    self.statement()
                nextToken=self.token.GetNextToken()
                if nextToken[0]=="}":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'do' expected at this area")
        self.XMLTree += "</doStatement>\n"

    def expressionList(self):
        if self.factorTest():
            self.XMLTree += "<expressionList>\n"
            self.expression()
            while True:
                nextToken=self.token.PeekNextToken()
                if nextToken[0]==",":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.GetNextToken()
                    self.expression()
                else:
                    break
            self.XMLTree += "</expressionList>\n"

    def returnStatement(self):
        nextToken=self.token.GetNextToken()
        self.XMLTree += "<returnStatement>\n"
        if nextToken[0]=="return":
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            if self.factorTest():
                self.expression()
            nextToken=self.token.GetNextToken()
            if nextToken[0]==";":
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'return' expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'return' expected at this area")
        self.XMLTree += "</returnStatement>\n"

    def expression(self):
        self.XMLTree += "<expression>\n"
        if self.factorTest():
            self.relationalExpression()
            nextToken=self.token.PeekNextToken()
            while nextToken[0]=='&' or nextToken[0]=='|':
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                self.relationalExpression()
                nextToken=self.token.PeekNextToken()
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a relational expression at this area")
        self.XMLTree += "</expression>\n"

    def relationalExpression(self):
        self.XMLTree += "<relationalExpression>\n"
        if self.factorTest():
            self.arithmeticExpression()
            nextToken=self.token.PeekNextToken()
            while True:
                # I think the provided full jack grammar make a mistake here, so I correct it.
                if nextToken[0]=="=":
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.GetNextToken()
                    self.arithmeticExpression()
                    nextToken=self.token.PeekNextToken()
                elif nextToken[0]=='>' or nextToken[0]=='<':
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                    nextToken=self.token.GetNextToken()
                    nextToken=self.token.PeekNextToken()
                    if nextToken[0]=="=":
                        self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                        nextToken=self.token.GetNextToken()
                    self.arithmeticExpression()
                    nextToken=self.token.PeekNextToken()
                else:
                    break
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a arithmetic expression at this area")
        self.XMLTree += "</relationalExpression>\n"

    def arithmeticExpression(self):
        self.XMLTree += "<arithmeticExpression>\n"
        if self.factorTest():
            self.term()
            nextToken=self.token.PeekNextToken()
            while nextToken[0]=='+' or nextToken[0]=='-':
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                self.term()
                nextToken=self.token.PeekNextToken()
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a term at this area")
        self.XMLTree += "</arithmeticExpression>\n"

    def term(self):
        self.XMLTree += "<term>\n"
        if self.factorTest():
            self.factor()
            nextToken=self.token.PeekNextToken()
            while nextToken[0]=='*' or nextToken[0]=='/':
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                self.factor()
                nextToken=self.token.PeekNextToken()
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a factor at this area")
        self.XMLTree += "</term>\n"

    def factor(self):
        self.XMLTree += "<factor>\n"
        nextToken=self.token.PeekNextToken()
        if nextToken[0]=='-' or nextToken[0]=='!':
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            nextToken=self.token.GetNextToken()
        nextToken=self.token.GetNextToken()
        if nextToken[1]=='Integer' or nextToken[1]=='String' or nextToken[0]=='true' or nextToken[0]=='false' or nextToken[0]=='null' or nextToken[0]=='this':
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
        elif nextToken[1]=='Identifier':
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            nextToken=self.token.PeekNextToken()
            if nextToken[0]=='.':
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                nextToken=self.token.GetNextToken()
                if nextToken[1]=='Identifier':
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a identifier at this area")
                nextToken=self.token.PeekNextToken()
            if nextToken[0]=='[':
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]==']':
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"']' expected at this area")
            if nextToken[0]=='(':
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                nextToken=self.token.GetNextToken()
                self.expressionList()
                nextToken=self.token.GetNextToken()
                if nextToken[0]==')':
                    self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"')' expected at this area")
        elif nextToken[0]=='(':
            self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            self.expressionList()
            nextToken=self.token.GetNextToken()
            if nextToken[0]==')':
                self.XMLTree += "<terminal> " + self.XMLEscape(nextToken) + " </terminal>\n"
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"')' expected at this area")
        self.XMLTree += "</factor>\n"
        
    # using to test whether it's possibly an expression by checking it's head        
    def factorTest(self):
        nextToken=self.token.PeekNextToken()
        if nextToken[1]=='Integer' or nextToken[0]=='-' or nextToken[0]=='!' or nextToken[1]=='String' or nextToken[0]=='true' or nextToken[0]=='false' or nextToken[0]=='null' or nextToken[0]=='this' or nextToken[1]=='Identifier' or nextToken[0]=='(':
            return True
        else:
            return False