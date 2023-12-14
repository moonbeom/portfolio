package portfolioDev.portfolio.controller.form;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
public class ContactForm {
    private String name;
    private String email;
    private String message;
}
