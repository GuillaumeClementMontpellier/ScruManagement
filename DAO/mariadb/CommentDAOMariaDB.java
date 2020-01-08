package DAO.mariadb;

import DAO.CommentDAO;
import business.system.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAOMariaDB extends CommentDAO {
    public CommentDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    @Override
    public Comment[] getComments(Commentable commentable) throws SQLException {
        int type = this.getCorrectType(commentable);
        if (type == -1){
            return null;
        }
        String sql = "SELECT * FROM Comment WHERE type = ? AND idCommentable = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1,type);
        pre.setInt(2,commentable.getId());
        ResultSet resultSet = pre.executeQuery();

        ArrayList<Comment> solution = new ArrayList();
        int id;
        int idUser;
        String description;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            idUser =  resultSet.getInt("owner");
            description = resultSet.getString("commentText");
            solution.add(new Comment(id,idUser,description));
        }
        Comment[] soos = solution.toArray(new Comment[solution.size()]);
        return soos;
    }

    @Override
    public boolean addComment(Commentable commentable, User user, String msg) throws SQLException {
        int type = this.getCorrectType(commentable);
        if (type == -1){
            return false;
        }
        String sql = "INSERT INTO Comment(type,idCommentable,owner,commentText) Values (?,?,?,?)";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1,type);
        pre.setInt(2,commentable.getId());
        pre.setInt(3,user.getId());
        pre.setString(4, msg);
        pre.execute();
        return true;
    }

    @Override
    public void deleteComment(Comment comment) throws SQLException {
        String sql ="DELETE FROM Comment WHERE id = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1,comment.getId());
        pre.execute();
    }

    @Override
    public void editComment(Comment comment, String msg) throws SQLException {
        String sql ="UPDATE Comment SET commentText = ? WHERE id = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setString(1, msg);
        pre.setInt(2,comment.getId());
        pre.execute();
    }

    //type 1 = backlog
    //type 2 = column
    //type 3 = userStory
    //type 4 = ticket
    private int getCorrectType(Commentable commentable) {
        if (commentable instanceof Backlog) {
            return 1;
        } else if (commentable instanceof Column) {
            return 2;
        } else if (commentable instanceof UserStory) {
            return 3;
        } else if (commentable instanceof Ticket) {
            return 4;
        } else {
            return -1;
        }
    }
}
