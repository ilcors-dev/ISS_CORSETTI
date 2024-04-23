### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('sonarqak24Arch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxsonarqak24', graph_attr=nodeattr):
          sonar24=Custom('sonar24','./qakicons/symActorSmall.png')
          sonardevice=Custom('sonardevice','./qakicons/symActorSmall.png')
          datacleaner=Custom('datacleaner','./qakicons/symActorSmall.png')
          distancefilter=Custom('distancefilter','./qakicons/symActorSmall.png')
     distancefilter >> Edge( label='obstacle', **eventedgeattr, decorate='true', fontcolor='red') >> sonar24
     sonardevice >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> datacleaner
     datacleaner >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> distancefilter
     sonar24 >> Edge(color='blue', style='solid',  decorate='true', label='<sonarstart &nbsp; >',  fontcolor='blue') >> sonardevice
diag
