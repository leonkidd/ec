Easy Cluster
============
将单实例服务变成集群服务。

# 原理
Client <-- 1:1 --> Agent <-- n:1 --> Server <br/>

Client <-- Socket -->  || <-- Socket --> Server <br/>
Client <-- Socket -->  || <-- Socket --> Server <br/>
Client <-- Socket -->  || <-- Socket --> Server <br/>
Client <-- Socket -->  || <br/>
Client <-- Socket -->  || <br/>
