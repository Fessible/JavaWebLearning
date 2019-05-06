package dao;

import bean.FileUploadBean;

import java.util.List;

public interface FileUploadDao {

    List<FileUploadBean> getFiles();


    void save(FileUploadBean bean);


}
