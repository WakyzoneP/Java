package testing;

import eu.ase.test.ElectronicDevices;
import eu.ase.test.Phone;
import eu.ase.test.SmartPhone;
import eu.ase.test.TCPServerSocketMultiT;
import eu.ase.test.UDPClientSocket;
import eu.ase.test.UDPServerSocket;
import eu.ase.test.Utils;
import eu.ase.test.VectThread;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitEvaluation {
	@Test
	public void _310testInfoClassPhone_mark3() throws Exception {
		Class<?> t = Class.forName("eu.ase.test.Phone");
		if (t.getDeclaredFields().length < 3) {
			Assert.fail("Not proper number of the fields");
		}

		Field[] var5;
		int var4 = (var5 = t.getDeclaredFields()).length;

		for (int var3 = 0; var3 < var4; ++var3) {
			Field f = var5[var3];

			try {
				Assert.assertTrue("The field " + f.toString() + " is private", Modifier.isPrivate(f.getModifiers()));
				if (f.getName().compareTo("weight") == 0) {
					Assert.assertEquals("The 'weight' is type float", Float.TYPE, f.getType());
				}

				if (f.getName().compareTo("producer") == 0) {
					Assert.assertEquals("The field 'producer' is String", String.class, f.getType());
				}

				if (f.getName().compareTo("diagonal") == 0) {
					Assert.assertEquals("The 'diagonal' is type double", Double.TYPE, f.getType());
				}
			} catch (Exception var7) {
				Assert.fail("The field " + f.toString() + " has problems in class Phone.");
			}
		}

	}

	@Test
	public void _311testInfoClassSmartPhone_mark3() throws Exception {
		Class<?> t = Class.forName("eu.ase.test.SmartPhone");
		if (t.getDeclaredFields().length < 1) {
			Assert.fail("Not proper number of the fields");
		}

		Field[] var5;
		int var4 = (var5 = t.getDeclaredFields()).length;

		for (int var3 = 0; var3 < var4; ++var3) {
			Field f = var5[var3];

			try {
				Assert.assertTrue("The field " + f.toString() + " is private", Modifier.isPrivate(f.getModifiers()));
				if (f.getName().compareTo("batteryDuration") == 0) {
					Assert.assertEquals("The 'batteryDuration' is type double", Integer.TYPE, f.getType());
				}
			} catch (Exception var7) {
				Assert.fail("The field " + f.toString() + " has problems in class SmartPhone.");
			}
		}

	}

	@Test
	public void _312testSetProducer_mark3() throws Exception {
		Phone t = new Phone();
		t.setProducer("prod");
		Assert.assertEquals("prod", t.getProducer());
		t.setProducer("prod1");
		Assert.assertEquals("prod1", t.getProducer());
		t.setProducer("prod2");
		Assert.assertEquals("prod2", t.getProducer());
	}

	@Test
	public void _313testSetProducerException_mark3() {
		Phone t = new Phone();

		try {
			t.setProducer("a");
			Assert.fail("setProducer accepts ONLY ONE CHAR");
		} catch (Exception var5) {
			;
		}

		try {
			t.setProducer("");
			Assert.fail("setProducer accepts EmptyString");
		} catch (Exception var4) {
			;
		}

		try {
			t.setProducer((String) null);
			Assert.fail("setProducer accepts null");
		} catch (Exception var3) {
			;
		}

	}

	@Test
	public void _314testInfoDevice_mark3() throws Exception {
		Phone t = new Phone();
		t.setProducer("prod");
		Assert.assertEquals("prod", t.infoDevice());
		t.setProducer("prod1");
		Assert.assertEquals("prod1", t.infoDevice());
		t.setProducer("prod2");
		Assert.assertEquals("prod2", t.infoDevice());
	}

	@Test
	public void _315testSetDiagonal_mark3() throws Exception {
		Phone t = new Phone();
		t.setDiagonal(3.5D);
		Assert.assertEquals(3.5D, t.getDiagonal(), 0.1D);
		t.setDiagonal(5.0D);
		Assert.assertEquals(5.0D, t.getDiagonal(), 0.1D);
		t.setDiagonal(8.2D);
		Assert.assertEquals(8.2D, t.getDiagonal(), 0.1D);
	}

	@Test
	public void _316testSetDiagonalException_mark3() {
		Phone t = new Phone();

		try {
			t.setDiagonal(0.0D);
			Assert.fail("setDiagonal accepts 0 - it must NOT");
		} catch (Exception var4) {
			;
		}

		try {
			t.setDiagonal(-5.0D);
			Assert.fail("setProducer accepts negative values - it MUST NOT");
		} catch (Exception var3) {
			;
		}

	}

	@Test
	public void _317testClone_mark3() throws Exception {
		Phone t1 = new Phone();
		t1.setProducer("P1");
		Phone t2 = (Phone) t1.clone();
		t2.setProducer(new String("P1"));
		Assert.assertNotSame(t1, t2);
		if (t1.getWeight() != t2.getWeight()) {
			Assert.fail("clone not correct implemented");
		}

		if (t1.getDiagonal() != t2.getDiagonal()) {
			Assert.fail("clone not correct implemented");
		}

		if (t1.getProducer() == t2.getProducer() || t1.getProducer().compareTo(t2.getProducer()) != 0) {
			Assert.fail("clone not correct implemented");
		}

	}

	@Test
	public void _318testSetBatteryDurationInSmartPhone_mark3() throws Exception {
		SmartPhone t = new SmartPhone();
		t.setBatteryDuration(3);
		Assert.assertEquals(3.0D, (double) t.getBatteryDuration(), 0.1D);
		t.setBatteryDuration(5);
		Assert.assertEquals(5.0D, (double) t.getBatteryDuration(), 0.1D);
		t.setBatteryDuration(8);
		Assert.assertEquals(8.0D, (double) t.getBatteryDuration(), 0.1D);
	}

	@Test
	public void _319testSetBatteryDurationInSmartPhoneException_mark3() {
		SmartPhone t = new SmartPhone();

		try {
			t.setBatteryDuration(0);
			Assert.fail("setBatteryDuration accepts 0 - it MUST NOT");
		} catch (Exception var4) {
			;
		}

		try {
			t.setBatteryDuration(-5);
			Assert.fail("setBatteryDuration accepts negative values - it MUST NOT");
		} catch (Exception var3) {
			;
		}

	}

	@Test
	public void _320testInfoDeviceSmartphone_mark3() throws Exception {
		SmartPhone t = new SmartPhone();
		t.setBatteryDuration(5);
		Assert.assertEquals("5", t.infoDevice());
		t.setBatteryDuration(7);
		Assert.assertEquals("7", t.infoDevice());
		t.setBatteryDuration(20);
		Assert.assertEquals("20", t.infoDevice());
	}

	@Test
	public void _410testCreatePhones_mark4() throws Exception {
		ArrayList lista = (ArrayList) Utils.createPhones(6);

		try {
			Assert.assertEquals(6L, (long) lista.size());
		} catch (Exception var4) {
			Assert.fail("createPhones() method returns null");
		}

		lista = (ArrayList) Utils.createPhones(10);

		try {
			Assert.assertEquals(10L, (long) lista.size());
		} catch (Exception var3) {
			Assert.fail("createPhones() method returns null");
		}

	}

	@Test
	public void _411testCreatePhonesException_mark4() {
		ArrayList lista;
		try {
			lista = (ArrayList) Utils.createPhones(0);
			if (lista != null) {
				Assert.fail("It has been created a list with 0 phones - it MUST NOT");
			}
		} catch (Exception var3) {
			;
		}

		try {
			lista = (ArrayList) Utils.createPhones(-3);
			if (lista != null) {
				Assert.fail("It has been created a list with negative number of phones - it MUST NOT");
			}
		} catch (Exception var2) {
			;
		}

	}

	@Test
	public void _412testReadPhones_mark4() throws Exception {
		ArrayList lista = (ArrayList) Utils.readPhones("phonesList.txt");

		try {
			Assert.assertEquals(3L, (long) lista.size());
		} catch (Exception var3) {
			Assert.fail("It must be 3 items/Phones into the file.");
		}

		Phone t = (Phone) lista.get(0);
		Assert.assertEquals("Samsung", t.getProducer());
		Assert.assertEquals(7.0D, t.getDiagonal(), 0.1D);
		t = (Phone) lista.get(1);
		Assert.assertEquals("Apple", t.getProducer());
		Assert.assertEquals(6.0D, t.getDiagonal(), 0.1D);
		t = (Phone) lista.get(2);
		Assert.assertEquals("LG", t.getProducer());
		Assert.assertEquals(7.0D, t.getDiagonal(), 0.1D);
	}

//
	@Test
	public void _413testReadPhonesException_mark4() {
		try {
			ArrayList<ElectronicDevices> lista = (ArrayList) Utils.readPhones("tttt.ttt");
			if (lista != null) {
				Assert.fail("IT must not be read from an inexisting file");
			}
		} catch (Exception var2) {

		}
	}

//   }
//
	@Test
	public void _414testBinaryPhones_mark4() throws Exception {
		ArrayList<ElectronicDevices> lista = (ArrayList) Utils.createPhones(3);
		if (lista != null) {
			for (int i = 0; i < lista.size(); ++i) {
				Phone t = (Phone) lista.get(i);
				t.setDiagonal((double) (i + 1));
				t.setProducer("Prod" + i);
				t.setWeight((float) (i + 101));
			}

			Utils.writeBinaryPhones("f.dat", lista);
			ArrayList<ElectronicDevices> lista2 = (ArrayList) Utils.readBinaryPhones("f.dat");
			File f = new File("f.dat");
			if (f.exists()) {
				f.delete();
			}

			Assert.assertEquals((long) lista2.size(), 3L);
			Assert.assertNotSame(lista, lista2);

			for (int idx = 0; idx < lista.size(); ++idx) {
				ElectronicDevices p1 = (ElectronicDevices) lista.get(idx);
				ElectronicDevices p2 = (ElectronicDevices) lista2.get(idx);
				if (p1 == p2 || !p1.equals(p2)) {
					Assert.fail(
							"createPhones / writeBinaryPhones / readBinaryPhones related to clone/equals aren't correct");
				}
			}
		} else {
			Assert.fail("createPhones / writeBinaryPhones / readBinaryPhones aren't correct");
		}

	}

	@Test
	public void _510testThreads_mark5() throws Exception {
		ArrayList<ElectronicDevices> lista = (ArrayList) Utils.createPhones(3);
		if (lista != null) {
			for (int i = 0; i < lista.size(); ++i) {
				Phone t = (Phone) lista.get(i);
				t.setDiagonal((double) (i + 1));
				t.setProducer("Prod" + i);
				t.setWeight((float) (i + 102));
			}

			Utils.writeBinaryPhones("f.dat", lista);
			ArrayList<ElectronicDevices> lista2 = (ArrayList) Utils.readBinaryPhones("f.dat");
			double avgW1 = 0.0D;

			for (int idx = 0; idx < lista2.size(); ++idx) {
				ElectronicDevices p2 = (ElectronicDevices) lista2.get(idx);
				avgW1 += (double) ((Phone) p2).getWeight();
			}

			avgW1 /= (double) lista2.size();
			VectThread vt = new VectThread("f.dat");
			Thread t = new Thread(vt);
			t.start();
			t.join();
			double avgW2 = vt.getAvgWeight();
			File f = new File("f.dat");
			if (f.exists()) {
				f.delete();
			}

			Assert.assertEquals((long) lista2.size(), 3L);
			Assert.assertEquals(avgW1, avgW2, 0.001D);
		} else {
			Assert.fail("Multithreading class mark 5 isn't correct");
		}

	}

	@Test
	public void _611testTCPServerMultiThread_Plus1or2EqMark6() {
		try {
			List<ElectronicDevices> list1 = (ArrayList) Utils.createPhones(3);
			if (list1 != null) {
				for (int i = 0; i < list1.size(); ++i) {
					Phone t = (Phone) list1.get(i);
					t.setDiagonal((double) (i + 1));
					t.setProducer("Prod" + i);
					t.setWeight((float) (i + 102));
				}

				Utils.writeBinaryPhones("myf.dat", list1);
				List<ElectronicDevices> list2 = (ArrayList) Utils.readBinaryPhones("myf.dat");

				TCPServerSocketMultiT serv = new TCPServerSocketMultiT(50001);
				serv.setFileName("myf.dat");

				Runnable thS = () -> {
					System.out.println("A ajuns aici");

					try {
						System.out.println("In try");
						serv.startTCPServer();
					} catch (Exception var2) {
						System.out.println("??????????");
						Assert.fail("TCP server class isn't correct");
					}

				};
				Thread tS = new Thread(thS);
				tS.start();
				System.out.println("A ajuns aici");
				Runnable thC = () -> {
					try {
						Socket s = new Socket("127.0.0.1", serv.getPort());
						PrintWriter out = new PrintWriter(s.getOutputStream(), true);
						ObjectInputStream in = new ObjectInputStream(s.getInputStream());
						out.println("GETFILE");
						List<ElectronicDevices> list3 = (List) in.readObject();
						Assert.assertEquals((long) list3.size(), 3L);

						for (int idxx = 0; idxx < list3.size(); ++idxx) {
							ElectronicDevices p1 = (ElectronicDevices) list2.get(idxx);
							ElectronicDevices p2 = (ElectronicDevices) list3.get(idxx);
							if (p1 == p2 || !p1.equals(p2)) {
								Assert.fail("TCP Socket serialization isn't correct");
							}
						}

						out.println("EXIT");
						out.close();
						s.close();
					} catch (Exception var9) {
						Assert.fail("Connectivity/protocol to the TCP server class isn't correct");
					}

				};
				Thread tC = new Thread(thC);
				tC.start();
				tC.join();
				File f = new File("myf.dat");
				if (f.exists()) {
					f.delete();
				}
			}
		} catch (Exception var9) {
			Assert.fail("Connectivity/protocol to the TCP server class isn't correct");
		}

	}

	@Test
	public void _612testTCPServerMultiThread_Plus1or2EqMark7() {
		try {
			List<ElectronicDevices> list1 = (ArrayList) Utils.createPhones(3);
			if (list1 != null) {
				for (int i = 0; i < list1.size(); ++i) {
					Phone t = (Phone) list1.get(i);
					t.setDiagonal((double) (i + 1));
					t.setProducer("Prod" + i);
					t.setWeight((float) (i + 102));
				}

				Utils.writeBinaryPhones("myf.dat", list1);
				List<ElectronicDevices> list2 = (ArrayList) Utils.readBinaryPhones("myf.dat");
				TCPServerSocketMultiT serv = new TCPServerSocketMultiT(50100);
				serv.setFileName("myf.dat");
				Runnable thS = () -> {
					try {
						serv.startTCPServer();
					} catch (Exception var2) {
						Assert.fail("TCP server class isn't correct");
					}

				};
				Thread tS = new Thread(thS);
				tS.start();
				Runnable thC = () -> {
					try {
						Socket s = new Socket("127.0.0.1", serv.getPort());
						PrintWriter out = new PrintWriter(s.getOutputStream(), true);
						ObjectInputStream in = new ObjectInputStream(s.getInputStream());
						out.println("GETJSON");
						String jsonIn = in.readUTF();
						Assert.assertEquals((long) list2.size(), 3L);
						JSONObject dataset = new JSONObject();
						dataset.put("phones", new JSONArray());
						JSONObject[] jsonarrays = new JSONObject[list2.size()];

						for (int i = 0; i < list2.size(); ++i) {
							jsonarrays[i] = new JSONObject();
							Phone p = (Phone) list2.get(i);
							jsonarrays[i].put("weight", (double) p.getWeight());
							jsonarrays[i].put("diagonal", p.getDiagonal());
							jsonarrays[i].put("producer", p.getProducer());
							dataset.append("phones", jsonarrays[i]);
						}

						String strJson = dataset.toString();
						System.out.println("jsonIn = " + jsonIn);
						out.println("EXIT");
						out.close();
						s.close();
						Assert.assertEquals(strJson, jsonIn);
					} catch (Exception var10) {
						Assert.fail("Connectivity/protocol to the TCP server class isn't correct");
					}

				};
				Thread tC = new Thread(thC);
				tC.start();
				tC.join();
				File f = new File("myf.dat");
				if (f.exists()) {
					f.delete();
				}
			}
		} catch (Exception var9) {
			Assert.fail("Connectivity/protocol to the TCP server class isn't correct");
		}

	}

	@Test
	public void _810testTCPServerMultiThreadDAO_Plus1EqMark8() {
		try {
			Connection c = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			Statement stmt = c.createStatement();
			String sqlDropTable = "drop table PHONES";
			stmt.executeUpdate(sqlDropTable);
			String sqlCreateTable = "create table PHONES(ID INT PRIMARY KEY NOT NULL,PRODUCER TEXT NOT NULL,DIAGONAL REAL, WEIGHT REAL)";
			stmt.executeUpdate(sqlCreateTable);
			stmt.close();
			c.commit();
			PreparedStatement ps = c
					.prepareStatement("insert into PHONES(ID, PRODUCER, DIAGONAL, WEIGHT) values (?, ?, ?, ?)");
			ps.setInt(1, 1);
			ps.setString(2, "Samsung");
			ps.setFloat(3, 7.5F);
			ps.setFloat(4, 120.0F);
			ps.executeUpdate();
			ps.setInt(1, 2);
			ps.setString(2, "Apple iPhone X");
			ps.setFloat(3, 8.5F);
			ps.setFloat(4, 110.0F);
			ps.executeUpdate();
			ps.close();
			c.commit();
			stmt = c.createStatement();
			String sqlSelect = "select * from PHONES";
			StringBuffer sb = new StringBuffer();
			ResultSet rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				int id = rs.getInt("id");
				String producer = rs.getString("PRODUCER");
				float diagonal = rs.getFloat("DIAGONAL");
				float weight = rs.getFloat("WEIGHT");
				sb.append(id + ":" + producer + ":" + diagonal + ":" + weight + "\r\n");
				System.out.println(sb);
			}

			stmt.close();
			c.close();
			String dbStrResult = sb.toString();
			TCPServerSocketMultiT serv = new TCPServerSocketMultiT(50200);
			serv.setFileName((String) null);
			Runnable thS = () -> {
				try {
					serv.startTCPServer();
				} catch (Exception var2) {
					Assert.fail("TCP server class isn't correct");
				}

			};
			Thread tS = new Thread(thS);
			tS.start();
			Runnable thC = () -> {
				try {
					Socket s = new Socket("127.0.0.1", serv.getPort());
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);
					ObjectInputStream in = new ObjectInputStream(s.getInputStream());
					out.println("GETDB");
					String dbStr = in.readUTF();
					out.println("EXIT");
					out.close();
					s.close();
					Assert.assertEquals(dbStrResult, dbStr);
				} catch (Exception var6) {
					Assert.fail("JDBC - Connectivity/protocol to the TCP server class isn't correct");
				}
			};
			Thread tC = new Thread(thC);
			tC.start();
			tC.join();
		} catch (Exception var15) {
			Assert.fail("JDBC - Connectivity/protocol to the TCP server class isn't correct");
		}

	}

//   @Test
//   public void _910testUDPServerClientPlus1or2_EqMark9_10() throws Exception {
//      try {
//         Throwable var1 = null;
//         Object var2 = null;
//
//         try {
//            UDPServerSocket uss = new UDPServerSocket();
//
//            try {
//               UDPClientSocket ucs = new UDPClientSocket();
//
//               try {
//                  Runnable th1 = () -> {
//                     try {
//                        uss.processRequest();
//                     } catch (Exception var2) {
//                        Assert.fail("UDP server class isn't correct");
//                     }
//
//                  };
//                  Runnable[] thArr = new Runnable[]{() -> {
//                     try {
//                        String r1 = ucs.sendAndReceiveMsg("W?", "127.0.0.1", uss.getBindPort());
//                        System.out.println("r1 = " + r1);
//                        Assert.assertEquals("UDPS", r1);
//                     } catch (Exception var3) {
//                        Assert.fail("UDP client class isn't correct");
//                     }
//
//                  }, () -> {
//                     try {
//                        String r2 = ucs.sendAndReceiveMsg("TEST", "127.0.0.1", uss.getBindPort());
//                        System.out.println("r2 = " + r2);
//                        Assert.assertEquals("ACK", r2);
//                     } catch (Exception var3) {
//                        Assert.fail("UDP client class isn't correct");
//                     }
//
//                  }, () -> {
//                     try {
//                        String r3 = ucs.sendAndReceiveMsg("BYE", "127.0.0.1", uss.getBindPort());
//                        System.out.println("r3 = " + r3);
//                        Assert.assertEquals("BYE ACK", r3);
//                     } catch (Exception var3) {
//                        Assert.fail("UDP client class isn't correct");
//                     }
//
//                  }};
//                  Thread[] tArr = new Thread[]{new Thread(thArr[0]), new Thread(thArr[1]), new Thread(thArr[2])};
//
//                  for(int i = 0; i < tArr.length; ++i) {
//                     Thread t1 = new Thread(th1);
//                     t1.start();
//                     tArr[i].start();
//                     tArr[i].join();
//                     t1.join();
//                  }
//               } finally {
//                  if (ucs != null) {
//                     ucs.close();
//                  }
//
//               }
//            } catch (Throwable var23) {
//               if (var1 == null) {
//                  var1 = var23;
//               } else if (var1 != var23) {
//                  var1.addSuppressed(var23);
//               }
//
//               if (uss != null) {
//                  uss.close();
//               }
//
//               throw var1;
//            }
//
//            if (uss != null) {
//               uss.close();
//            }
//         } catch (Throwable var24) {
//            if (var1 == null) {
//               var1 = var24;
//            } else if (var1 != var24) {
//               var1.addSuppressed(var24);
//            }
//
//            throw var1;
//         }
//      } catch (IOException var25) {
//         Assert.fail("JDBC - TCP client/server class isn't correct");
//      }
//
//  }
}
