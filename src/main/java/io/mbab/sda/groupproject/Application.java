package io.mbab.sda.groupproject;

import io.mbab.sda.groupproject.config.Configuration;
import io.mbab.sda.groupproject.config.JpaUtil;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MainAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CrudRepositoryFactory;

public class Application {

  public static void main(String... args) {
    var emFactory =
        JpaUtil.getEntityManagerFactory(
            Configuration.getDataSource(), Configuration.getEntityClass());
    var repositoryFactory = new CrudRepositoryFactory(emFactory);
    var scanner = new CustomScanner();

    new MenuActionContext(scanner, repositoryFactory, emFactory.createEntityManager())
        .use(MainAction.class)
        .execute();
  }
}
