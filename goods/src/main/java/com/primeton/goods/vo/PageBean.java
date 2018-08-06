package com.primeton.goods.vo;

/**
 * @Author: Usher
 * @Description:
 */
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = 2587400287343042431L;
    private Integer currentPage;
    private Integer totalPage;
    private Integer size = 4;// ÿҳ��ʾ�ļ�¼��
    private Integer count; //�ܼ�¼��
    private List<T> pageInfos;// ÿҳ��ѯ���ķ�ҳ����




}

