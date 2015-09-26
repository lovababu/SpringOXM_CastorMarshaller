package org.avol.springoxm.castor.beans;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Lovababu on 9/13/2015.
 */

@Data
@XmlRootElement
public class StudentBean {

    private String name;
    private String address;
}
