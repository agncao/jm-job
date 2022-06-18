package com.jm.job.core.repository

import com.jm.job.core.model.Node
import com.jm.job.core.model.Task
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface TaskRepository {

    /**
     * 新增一个任务
     * @param task
     * @return
     */
    @Insert("""<script>
        INSERT INTO task
            (name,cron,status,group_id,success_count,fail_count,disable,retry) 
        VALUES 
            (#{m.name}, #{m.cron}, 0,#{m.groupId}, 0, 0,0,0)
    </script>""")
    Task insert(@Param("m") Task task);

    /**
     * 禁用/重新启用任务
     * @param id 任务id
     * @param disable 0启用1禁用
     * @return
     */
    @Update("""<script>
        update task set disable = #{disable} where id = #{id} 
    </script>""")
    int disable(@Param("id")Long id,@Param("disable")int disable);

    /**
     * 更新任务状态
     * @param id
     * @param status
     * @return
     */
    @Update("""<script>
        update task set status = #{status} where id = #{id} and status &lt; #{status} and disable=0
    </script>""")
    int updateStatus(@Param("id")Long id,@Param("status")int status);

    /**
     * 手动重试,手动执行
     * @param id 任务id
     * @return
     */
    @Update("""<script>
        update task set retry = 1 where id = #{id} and status in (0,3) and disable=0
    </script>""")
    int retry(@Param("id")Long id);

    /**
     * 任务完成
     * @param taskId
     * @return
     */
    @Update("""<script>
        update task set retry = 0,status=0 where id=#{id}
    </script>""")
    int finish(@Param("taskId")Long taskId);

    /**
     * 查询多个分组的任务
     * @param groupIds
     * @return
     */
    @Select("""<script>
       select * from task where disable=0 and group_id in 
            <foreach item="groupId" index="index" collection="groupIds" open="(" separator="," close=")">
                #{groupId}
            </foreach>
    </script>""")
    List<Task> findTasksByGroupIds(@Param("groupIds")Set<Long> groupIds);

    /**
     * 查询重试的任务
     * @return
     */
    @Select("""<script>
       "select * from task where retry = 1 and status=0 and disable=0 and group_id in 
            <foreach item="groupId" index="index" collection="groupIds" open="(" separator="," close=")">
                #{groupId}
            </foreach>
    </script>""")
    List<Task> findRetriedTasks(@Param("groupIds")Set<Long> groupIds);
}