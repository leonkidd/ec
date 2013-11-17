Easy Cluster
============
将单实例服务变成集群服务。

# 原理
Client <-- Socket -->  |		| <-- Socket --> Server
Client <-- Socket -->  |		| <-- Socket --> Server
Client <-- Socket -->  |   EC	| <-- Socket --> Server
Client <-- Socket -->  |		|
Client <-- Socket -->  |		|