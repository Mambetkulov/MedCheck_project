package dao.interfacDao;

public interface GenericDao <T>{


    String add(int index , T t);

    void removeById(int index,int hosIndex);

    String updateById(int index,int index2, T t);
}
