package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation56(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        // Сравниваем пока только размеры списков:
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        // Добавляем в старый список ту группу, которую мы только что добавили в приложение
        assertThat(after, equalTo(before.WithAdded(group.withId(after.stream().
                mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


    @Test(enabled = false)
    public void testBadGroupCreation() {
        // Группа с ' в названии создаться не может, так что этот тест фактически неисправен
        app.goTo().groupPage();
        Groups before = (Groups) app.group().all();
        GroupData group = new GroupData().withName("Test 2'");
        app.group().create(group);
        // Сравниваем пока только размеры списков:
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }

    @Test(enabled = false)
    public void testGroupCreationSortedLists() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("Test 2").withHeader("New header").withFooter("New Footer");
        app.group().create(group);
        List<GroupData> after = app.group().list();
        // Сравниваем пока только размеры списков:
        Assert.assertEquals(after.size(), before.size() + 1);

        // Добавляем в старый список ту группу, которую мы только что добавили в приложение
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("Test1");
        app.group().create(group);
        List<GroupData> after = app.group().list();
        // Сравниваем пока только размеры списков:
        Assert.assertEquals(after.size(), before.size() + 1);

/*      Список превращаем в поток - stream, по нему пробегает функция и находит макс элемент,
        при этом сравниваются объекты типа GroupData, путём сравнения их id.
        На выходе этой функции будет макс объект - то есть группа с максимальным id.
        И нам остаётся только взять её id.
 */
        group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

        // Добавляем в старый список ту группу, которую мы только что добавили в приложение
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
