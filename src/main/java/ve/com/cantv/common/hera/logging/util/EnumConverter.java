package ve.com.cantv.common.hera.logging.util;

/**
 * Created by Liodegar.
 */
public interface EnumConverter <E extends Enum<E> & EnumConverter<E>> {

    byte getId();

    E convert(byte val);

    String getCode();

    E convert(String code);
}