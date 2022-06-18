package com.jm.job.core.repository

import com.jm.job.core.model.Group
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface GroupRepository {

    @Insert("""<script>
            INSERT INTO group(name) VALUES (#{m.name})
   	    </script>""")
    @Options(useGeneratedKeys = true,keyProperty = "id", keyColumn = "id")
    int insert(@Param("m")Group group);

    /**
     * 得到当前节点所属的分组
     * @param nodeId
     * @return
     */
    @Select("""<script>
        select * from group where node_id=#{nodeId}
    </script>""")
    List<Group> findListByNodeId(@Param("nodeId")Long nodeId);
}