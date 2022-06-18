package com.jm.job.core.repository

import com.jm.job.core.model.Node
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface NodeRepository {
    /**
     * 新增一个节点
     * @param node
     * @return
     */
    @Insert("""<script>
            INSERT INTO node(ip,port,group_id,disable) VALUES (#{m.ip}, #{m.port}, #{m.groupId}, 0)
   	    </script>""")
    @Options(useGeneratedKeys = true,keyProperty = "id", keyColumn = "id")
    int insert(@Param("m") Node node);

    /**
     * 禁用/重新启用节点
     * @param id 节点id
     * @param disable 0启用1禁用
     * @return
     */
    @Update("""<script>
       update node set disable = #{disable} where id = #{id}
    </script>""")
    int disable(@Param("id") Long id,@Param("disable") int disable);

    /**
     * 根据ip查询节点信息
     * @param ip
     * @return
     */
    @Select("""<script>
       select * from node where ip=#{ip}
    </script>""")
    Node getByIp(String ip);
}