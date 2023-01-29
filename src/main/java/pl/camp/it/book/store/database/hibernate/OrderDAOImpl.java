package pl.camp.it.book.store.database.hibernate;

import jdk.jshell.spi.ExecutionControl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.model.Order;

import java.util.List;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void persistOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.persist(order);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateOrder(Order order) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Niepotrzebna metoda !!");
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery(
                "FROM pl.camp.it.book.store.model.Order WHERE user_id = :userId",
                Order.class);
        query.setParameter("userId", userId);
        List<Order> orders = query.getResultList();
        session.close();
        return orders;
    }
}