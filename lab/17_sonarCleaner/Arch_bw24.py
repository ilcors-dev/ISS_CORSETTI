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
with Diagram('bw24Arch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxsonar', graph_attr=nodeattr):
          sonarnomqtt=Custom('sonarnomqtt','./qakicons/symActorSmall.png')
          sonardevice=Custom('sonardevice','./qakicons/symActorSmall.png')
          datacleaner=Custom('datacleaner','./qakicons/symActorSmall.png')
          distancefilter=Custom('distancefilter','./qakicons/symActorSmall.png')
     with Cluster('ctxcleaner24', graph_attr=nodeattr):
          cleaner24=Custom('cleaner24(ext)','./qakicons/externalQActor.png')
     distancefilter >> Edge( label='obstacle', **eventedgeattr, decorate='true', fontcolor='red') >> sonarnomqtt
     sonarnomqtt >> Edge( label='alarm', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonardevice >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonardevice >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> datacleaner
     datacleaner >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> distancefilter
     sonarnomqtt >> Edge(color='blue', style='solid',  decorate='true', label='<sonarstart &nbsp; >',  fontcolor='blue') >> sonardevice
diag
