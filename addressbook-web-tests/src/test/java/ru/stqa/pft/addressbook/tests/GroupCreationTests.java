package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test1", "header1", "footer1");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        // Сравниваем пока только размеры списков:
        Assert.assertEquals(after.size(), before.size() + 1);

/*      Список превращаем в поток - stream, по нему пробегает функция и находит макс элемент,
        при этом сравниваются объекты типа GroupData, путём сравнения их id.
        На выходе этой функции будет макс объект - то есть группа с максимальным id.
        И нам остаётся только взять её id.
 */
        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

        // Добавляем в старый список ту группу, которую мы только что добавили в приложение
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
