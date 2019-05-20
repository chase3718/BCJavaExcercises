package com.stuffy.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.stuffy.business.Stuffy;

public class StuffyDB {

	public static List<Stuffy> getAll() {
		List<Stuffy> stuffys = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			Query q = em.createQuery("Select u from Stuffy u");
			stuffys = q.getResultList();
		} finally {
			em.close();
		}

		return stuffys;
	}

	public static Stuffy get(int n) {
		Stuffy stuffy = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		TypedQuery<Stuffy> q = em.createQuery("Select u from Stuffy u Where u.id = :n", Stuffy.class);
		q.setParameter("n", n);
		try {
			stuffy = q.getSingleResult();
		} catch (NoResultException e) {
			System.err.println(e);
		} finally {
			em.close();
		}

		return stuffy;
	}

	public static void insert(Stuffy stuffy) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(stuffy);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Stuffy stuffy) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(stuffy);
			em.remove(stuffy);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(Stuffy stuffy) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(stuffy);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
}
