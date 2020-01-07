package DAO;

import business.system.Comment;
import business.system.Commentable;
import business.system.User;

import java.sql.SQLException;

public abstract class CommentDAO extends DAO {
    public CommentDAO(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }
    public abstract Comment[] getComments(Commentable commentable);
    public abstract void addComment(Commentable commentable, User user, String msg);
    public abstract void deleteComment(Comment comment);
    public abstract void editComment(Comment comment);
}
