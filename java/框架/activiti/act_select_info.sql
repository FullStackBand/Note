# RepositoryService
SELECT * FROM act_ge_bytearray;		#二进制文件表
SELECT * FROM act_re_deployment; 	#流程部署表
SELECT * FROM act_re_procdef;		#流程定义
SELECT * FROM act_ge_property;		#工作流的ID算法和版本信息

#RuntimeService
SELECT * FROM act_ru_execution;		#流程启动一次只要没有执行完成,就会有一条数据
SELECT * FROM act_ru_task;		#可能会有多条数据
SELECT * FROM act_ru_variable;		#记录流程运行时的流程变量
SELECT * FROM act_ru_identitylink;	#存放流程办理人的信息

#HistroyService
SELECT * FROM act_hi_procinst;		#历史流程实例
SELECT * FROM act_hi_taskinst;		#历史任务实例
SELECT * FROM act_hi_actinst;		#历史活动节点表
SELECT * FROM act_hi_varinst;		#历史流程变量表
SELECT * FROM act_hi_identitylink;	#历史办理人表
SELECT * FROM act_hi_comment;		#历史批注表
SELECT * FROM act_hi_attachment;	#历史附件表