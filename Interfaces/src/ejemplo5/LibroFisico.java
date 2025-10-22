/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo5;

/**
 *
 * @author Jeremi Alexander
 */
public class LibroFisico implements Libro {

    private String titulo;
    private String autor;
    private String ISBN;

    public LibroFisico(String titulo, String autor, String ISBN) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public String getISBN() {
        return ISBN;
    }

}
