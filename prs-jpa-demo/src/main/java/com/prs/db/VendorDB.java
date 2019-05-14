package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.prs.business.User;
import com.prs.business.Vendor;

public class VendorDB {

	public static List<Vendor> getAll() {
		List<Vendor> vendors = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			Query q = em.createQuery("Select v from Vendor v");
			vendors = q.getResultList();
		} finally {
			em.close();
		}

		return vendors;
	}

	public static Vendor get(int n) {
		Vendor vendor = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		TypedQuery<Vendor> q = em.createQuery("Select v from Vendor v Where v.id = :n", Vendor.class);
		q.setParameter("n", n);
		try {
			vendor = q.getSingleResult();
		} catch (NoResultException e) {
			System.err.println(e);
		} finally {
			em.close();
		}

		return vendor;
	}

	public static void insert(Vendor vendor) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(vendor);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Vendor vendor) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(vendor);
			em.remove(vendor);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(Vendor vendor) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(vendor);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

}
