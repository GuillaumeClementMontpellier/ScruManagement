package DAO;

import business.system.Comment;
import business.system.Commentable;
import business.system.User;

public interface CommentDAO {

    Comment[] getComments(Commentable commentable);

    void addComment(Commentable commentable, User user, String msg);

    void deleteComment(Comment comment);

    void editComment(Comment comment);
}
