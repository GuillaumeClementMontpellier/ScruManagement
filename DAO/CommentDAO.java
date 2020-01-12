package DAO;

import business.system.Comment;
import business.system.Commentable;
import business.system.User;

import java.sql.SQLException;

public interface CommentDAO {
    Comment[] getComments(Commentable commentable) throws SQLException;
    boolean addComment(Commentable commentable, User user, String msg) throws SQLException;
    void deleteComment(Comment comment) throws SQLException;
    void editComment(Comment comment, String msg) throws SQLException;
}
