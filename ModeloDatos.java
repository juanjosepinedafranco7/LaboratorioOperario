package logica;

import java.util.HashMap;
import entidades.Operario;

public class ModeloDatos {
    private HashMap<String, Operario> mapaOperarios;

    public ModeloDatos() {
        mapaOperarios = new HashMap<>();
    }

    public void registrarOperario(Operario oper) {
        mapaOperarios.put(oper.getDocumento(), oper);
    }

    public Operario consultarOperario(String doc) {
        return mapaOperarios.get(doc);
    }
}
