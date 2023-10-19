package br.com.bruninhaltorres.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    // Static: Não precisa instanciar a classe.

    public static void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
    
    // Vai pegar todas as propriedades nulas.
    public static String[] getNullPropertyNames(Object source){
        // BeanWrapper: Interface que vai permitir o acesso as propriedades dos objetos 
        // final: Seu valor não pode ser alterado depois da sua inicialização.
        final BeanWrapper src = new BeanWrapperImpl(source);

        // Vai gerar um array com todas as propriedades do objeto.
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // Se parece com List, mas não permite elementos duplicados e seu acesso deve ser feito por iteração e não por índice.
        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd : pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
