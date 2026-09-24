public class Libro {
    private String titulo;
    private String autor;
    private String codigo;
    private String genero;
    private int publicacion;
    private int copiasDisponibles;

    public Libro(String titulo, String autor, String codigo, String genero, int publicacion, int copiasDisponibles ){

        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.genero = genero;
        this.publicacion = publicacion;
        this.copiasDisponibles = copiasDisponibles;

    }
    public String getTitulo(){
        return titulo;

    }
    public void setTitulo(String titulo){
        this.titulo = titulo;

    }
    public String getAutor(){
        return autor;

    }
    public void setAutor(String autor){
        this.autor = autor;

    }
    public String getCodigo(){
        return codigo;

    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;

    }

    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }
    public String toString(){
        return "Titulo: " + titulo + ", Autor: " + autor + ",Codigo: " + codigo + ", Genero: " + genero + ", Publicacion: " + publicacion + ", Copias disponibles: " + copiasDisponibles;

    }
}