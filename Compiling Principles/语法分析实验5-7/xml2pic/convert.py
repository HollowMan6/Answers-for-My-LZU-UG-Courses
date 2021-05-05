#!/usr/bin/python3
# By Songlin Jiang, 320180901941

from xml.parsers import expat
from treelib import Tree


class Element:
    '''analyze a element'''

    def __init__(self, name, attributes, tree, number, parentNO):
        # record tag and attribute dictionary
        self.name = name
        self.attributes = attributes
        self.parentNO = parentNO
        self.number = number
        # clear the element cdata and its children
        self.cdata = ''
        self.children = []
        self.tree = tree

    def addChild(self, element):
        self.children.append(element)
        self.tree.create_node(element.name, element.number,
                              parent=element.parentNO)

    def getAttribute(self, key):
        return self.attributes.get(key)

    def getData(self):
        return self.cdata

    def getElements(self, name=''):
        if name:
            return [c for c in self.children if c.name == name]
        else:
            return list(self.children)

    def getTree(self):
        return self.tree


class Xml2Obj:
    '''transform XML to Object'''

    def __init__(self):
        self.root = None
        self.nodeStack = []
        self.numberCount = 0
        self.tree = Tree()

    def StartElement(self, name, attributes):
        'Expat start element event handler'
        # put the element into stack and make it become child_element
        if self.nodeStack:
            parent = self.nodeStack[-1]
            # make instance of class
            element = Element(name, attributes, self.tree,
                              self.numberCount, parent.number)
            parent.addChild(element)
            self.tree = parent.getTree()
        else:
            element = self.root = Element(
                name, attributes, self.tree, self.numberCount, None)
            self.tree.create_node(self.root.name, self.root.number)
        self.numberCount += 1
        self.nodeStack.append(element)

    def EndElement(self, name):
        'Expat end element event handler'
        if self.nodeStack[-1].cdata:
            self.tree.get_node(
                self.nodeStack[-1].number).tag += self.nodeStack[-1].cdata
        self.nodeStack.pop()

    def CharacterData(self, data):
        '''Expat character data event handler'''
        if data.strip():
            element = self.nodeStack[-1]
            element.cdata += data

    def showTree(self):
        self.tree.show(key=lambda node:node.identifier)
        # In https://github.com/caesar0301/treelib/pull/180 :
        # self.tree.show(sorting=False)

    def toDot(self, file):
        self.tree.to_graphviz(file)
        # In https://github.com/caesar0301/treelib/pull/179 :
        # self.tree.to_graphviz(file, sorting=False)

    def Parse(self, filename):
        # create Expat analyzer
        Parser = expat.ParserCreate()
        # Set the Expat event handlers to our methods
        Parser.StartElementHandler = self.StartElement
        Parser.EndElementHandler = self.EndElement
        Parser.CharacterDataHandler = self.CharacterData
        # analyz XML file
        Parser.Parse(open(filename).read(), 1)
        return self.root


if __name__ == "__main__":
    parser = Xml2Obj()
    parser.Parse('input.xml')
    parser.showTree()
    parser.toDot("output.dot")

    newContent = []
    toSort = {}
    with open("output.dot", "r") as f:
        content = f.readlines()
        import re
        for line in content:
            if "terminal (" in line:
                line = line.replace("shape=circle", "shape=square")
            number = ''.join(re.findall(r'"(.+?)" \[', line))
            if number:
                toSort[int(number)] = line
            else:
                newContent.append(line)
    newHead = newContent[0]
    newTail = newContent[1:]
    newContent = []
    for i in sorted(toSort):
        newContent.append(toSort[i])
    newContent.extend(newTail)
    newContent.insert(0, newHead)
    with open("output.dot", "w") as f:
        f.writelines(newContent)

    # dot -Tpng output.dot -o output.png
