package priv.cweiy.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import priv.cweiy.pro.entity.JavaBokeModel;

/**
 * @author yichen
 * @version V1.0
 * @ClassName: TestRepository
 * @Description: (用一句话描述该文件做什么)
 * @Date 2019/7/24 14:33
 */
@Repository
public interface TestRepository extends JpaRepository<JavaBokeModel, String> {
}
