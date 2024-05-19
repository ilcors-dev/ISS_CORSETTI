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
with Diagram('flipflopsystemArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxflipflop', graph_attr=nodeattr):
          flipflop=Custom('flipflop','./qakicons/symActorSmall.png')
          nor=Custom('nor','./qakicons/symActorSmall.png')
          flipflopmock=Custom('flipflopmock','./qakicons/symActorSmall.png')
     flipflop >> Edge(color='magenta', style='solid', decorate='true', label='<norSET<font color="darkgreen"> norSETReply</font> &nbsp; norRESET<font color="darkgreen"> norRESETReply</font> &nbsp; >',  fontcolor='magenta') >> nor
     flipflopmock >> Edge(color='magenta', style='solid', decorate='true', label='<state<font color="darkgreen"> output</font> &nbsp; >',  fontcolor='magenta') >> flipflop
diag
